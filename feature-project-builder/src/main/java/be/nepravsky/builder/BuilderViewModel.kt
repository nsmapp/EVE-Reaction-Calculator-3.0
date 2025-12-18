package be.nepravsky.builder

import androidx.lifecycle.viewModelScope
import be.nepravsky.builder.mapper.BpcShortModelMapper
import be.nepravsky.builder.mapper.ProjectBuildMapper
import be.nepravsky.builder.model.BpcShortModel
import be.nepravsky.builder.model.ProjectBuildSideEffect
import be.nepravsky.builder.model.ProjectBuilderState
import be.nepravsky.builder.model.ProjectItemModel
import be.nepravsky.sm.domain.model.query.ReactionsQuery
import be.nepravsky.sm.domain.usecase.GetBpcListUseCase
import be.nepravsky.sm.domain.usecase.groups.GetActiveGroupIdsUseCase
import be.nepravsky.sm.domain.usecase.project.GetProjectUseCase
import be.nepravsky.sm.domain.usecase.project.SaveProjectUseCase
import be.nepravsky.sm.evereactioncalculator.utils.TEXT_EMPTY
import be.nepravsky.sm.evereactioncalculator.viewmodel.BaseViewModel
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import org.koin.core.annotation.Factory
import org.koin.core.annotation.InjectedParam

@Factory
class BuilderViewModel(
    @InjectedParam val projectId: Long?,
    private val saveProjectUseCase: SaveProjectUseCase,
    private val projectBuildMapper: ProjectBuildMapper,
    private val getActiveGroupIdsUseCase: GetActiveGroupIdsUseCase,
    private val getProjectUseCase: GetProjectUseCase,
    private val getBpcListUseCase: GetBpcListUseCase,
    private val bpcShortModelMapper: BpcShortModelMapper,
) : BaseViewModel(), BuilderContract {

    private val _state = MutableStateFlow(ProjectBuilderState.EMPTY)
    val state = _state.asStateFlow()

    private val _sideEffect by lazy { Channel<ProjectBuildSideEffect>() }
    val sideEffect: Flow<ProjectBuildSideEffect> by lazy { _sideEffect.receiveAsFlow() }

    private val _activeGroupIds = MutableStateFlow<List<Long>>(emptyList())
    private var searchQuery = TEXT_EMPTY

    init {
        initData()
    }

    override fun initData() {
        getActiveReactionGroupIds()
        projectId?.let { id -> loadProject(id) }
            ?: _state.update { ProjectBuilderState.EMPTY.copy(
                items = mutableListOf()
            ) }
    }

    override fun setProjectName(name: String) {
        _state.update { it.copy(name = name) }
    }


    override fun saveProject() {
        viewModelScope.launch {
            val project = projectBuildMapper.mapProject(_state.value)
            saveProjectUseCase.save(project)
                .onSuccess {
                    _sideEffect.send(ProjectBuildSideEffect.CLOSE)
                }
        }
    }

    override fun showTypeBottomSheet() {
        _state.update { it.copy(isShowTypeBottomSheet = true) }
    }

    override fun hideTypeBottomSheet() {
        _state.update { it.copy(isShowTypeBottomSheet = false) }
    }

    override fun addProjectItem(type: BpcShortModel) {

        val items: List<ProjectItemModel> = _state.value.items
        val projectItem = ProjectItemModel(
            type.id,
            name = type.name,
            runCount = "1",
            me = 0.0,
            subMe = 0.0,
        )
        val updated = if (items.none { it.id == projectItem.id }) items + projectItem else items

        _state.update {
            it.copy(
                isShowTypeBottomSheet = false,
                items = updated
            )
        }
    }

    override fun deleteProjectItem(typeId: Long) {
        val items = _state.value.items.filter { it.id != typeId }
        _state.update {
            it.copy(
                items = items
            )
        }
    }

    override fun getBpcList(text: String) {
        searchQuery = text
        viewModelScope.launch {
            getBpcListUseCase.invoke(ReactionsQuery(text, _activeGroupIds.value))
                .onSuccess { bpcList ->
                    _state.update { state ->
                        state.copy(types = bpcList.map {
                            bpcShortModelMapper.map(
                                it
                            )
                        })
                    }
                }
                .onFailure {
                    TODO("getBpcListUseCase don't implemented")
                }
        }

    }

    override fun loadProject(projectId: Long) {
        viewModelScope.launch {
            getProjectUseCase.invoke(projectId)
                .onSuccess { project ->
                    _state.update { projectBuildMapper.map(project) }
                }
                .onFailure {
                    _state.update { ProjectBuilderState.EMPTY }
                }
        }
    }


    private fun getActiveReactionGroupIds() {
        viewModelScope.launch {
            getActiveGroupIdsUseCase.invoke()
                .collectLatest { ids ->
                    _activeGroupIds.update { ids }
                    getBpcList(searchQuery)
                }
        }
    }

    fun setRunCount(runs: String, id: Long) {
        val items = _state.value.items.map { item ->
            if (item.id == id) item.copy(runCount = runs) else item
        }

        _state.update {
            it.copy(
                items = items
            )
        }

    }
}