package be.belveb.builder

import androidx.lifecycle.viewModelScope
import be.belveb.builder.mapper.ProjectBuildMapper
import be.belveb.builder.model.ProjectBuildSideEffect
import be.belveb.builder.model.ProjectBuilderState
import be.nepravsky.sm.domain.usecase.project.SaveProjectUseCase
import be.nepravsky.sm.evereactioncalculator.viewmodel.BaseViewModel
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import org.koin.core.annotation.Factory

@Factory(binds = [BaseViewModel::class])
class BuilderViewModel(
    private val saveProjectUseCase: SaveProjectUseCase,
    private val projectBuildMapper: ProjectBuildMapper,
) : BaseViewModel(), BuilderContract {

    private val _state = MutableStateFlow(ProjectBuilderState.EMPTY)
    val state = _state.asStateFlow()

    private val _sideEffect by lazy { Channel<ProjectBuildSideEffect>() }
    val sideEffect: Flow<ProjectBuildSideEffect> by lazy { _sideEffect.receiveAsFlow() }

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
}