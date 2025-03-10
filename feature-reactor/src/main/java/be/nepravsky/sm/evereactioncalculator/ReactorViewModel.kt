package be.nepravsky.sm.evereactioncalculator

import androidx.lifecycle.viewModelScope
import be.nepravsky.sm.domain.model.project.Project
import be.nepravsky.sm.domain.model.query.ReactionQuery
import be.nepravsky.sm.domain.model.settings.PriceSource
import be.nepravsky.sm.domain.usecase.price.UpdatePriceUseCase
import be.nepravsky.sm.domain.usecase.project.GetProjectUseCase
import be.nepravsky.sm.domain.usecase.reactor.MakeReactionUseCase
import be.nepravsky.sm.domain.usecase.settings.GetPriceSourceUseCase
import be.nepravsky.sm.domain.usecase.settings.UpdateOfflineModeSettingUseCase
import be.nepravsky.sm.evereactioncalculator.mapper.ComplexReactionMapper
import be.nepravsky.sm.evereactioncalculator.mapper.ProjectMapper
import be.nepravsky.sm.evereactioncalculator.mapper.SharedReactionMapper
import be.nepravsky.sm.evereactioncalculator.model.ReactorSideEffect
import be.nepravsky.sm.evereactioncalculator.model.ReactorState
import be.nepravsky.sm.evereactioncalculator.viewmodel.BaseViewModel
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.debounce
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import org.koin.core.annotation.Factory
import org.koin.core.annotation.InjectedParam

@Factory(binds = [BaseViewModel::class])
class ReactorViewModel(
    @InjectedParam val reactionId: Long,
    @InjectedParam val isSingleReaction: Boolean,
    private val updatePriceUseCase: UpdatePriceUseCase,
    private val makeReactionUseCase: MakeReactionUseCase,
    private val getPriceSourceUseCase: GetPriceSourceUseCase,
    private val updateOfflineModeSettingUseCase: UpdateOfflineModeSettingUseCase,
    private val getProjectUseCase: GetProjectUseCase,
    private val complexReactionMapper: ComplexReactionMapper,
    private val sharedReactionMapper: SharedReactionMapper,
    private val projectMapper: ProjectMapper,
) : BaseViewModel(), ReactionContract {

    private val _state = MutableStateFlow(ReactorState.EMPTY)
    val state = _state.asStateFlow()

    private val _sideEffect by lazy { Channel<ReactorSideEffect>() }
    val sideEffect: Flow<ReactorSideEffect> by lazy { _sideEffect.receiveAsFlow() }


    private var projectQuery = listOf<ReactionQuery>()
    private var singleQuery = MutableStateFlow(ReactionQuery(reactionId))
    private val runFlow = MutableStateFlow(1L)
    private val meFlow = MutableStateFlow(0.0)
    private val subMeFlow = MutableStateFlow(0.0)


    init {
        initReactor()
        checkOfflineMode()

    }

    private fun initReactor() {
        if (isSingleReaction) updatePriceAndStartReaction()
        else viewModelScope.launch {
            getProjectUseCase.invoke(reactionId)
                .onSuccess { project -> handleProject(project) }
        }
    }

    private fun handleProject(project: Project) {
        projectQuery = projectMapper.mapToReactionQuery(project)
        updatePriceAndStartReaction()
    }

    private fun initTextWatchersFlows() {
        viewModelScope.launch {
            runFlow.debounce(INPUT_DEBOUNCE).collect { run ->
                singleQuery.update { it.copy(run = run) }
            }
        }

        viewModelScope.launch {
            meFlow.debounce(INPUT_DEBOUNCE).collect { me ->
                singleQuery.update { it.copy(me = me) }
            }
        }

        viewModelScope.launch {
            subMeFlow.debounce(INPUT_DEBOUNCE).collect { subMe ->
                singleQuery.update { it.copy(subMe = subMe) }
            }
        }

        viewModelScope.launch {
            singleQuery.debounce(INPUT_DEBOUNCE)
                .collect { query ->
                    makeReaction(listOf(query))
                    _state.update { it.copy(
                        run = query.run.toString(),
                        me = query.me.toString(),
                        subMe = query.subMe.toString(),
                    ) }
                }
        }
    }

    override fun setRuns(run: Long) {
        runFlow.value = run
    }

    override fun setMe(me: Double) {
        meFlow.value = me
    }

    override fun setSubMe(subMe: Double) {
        subMeFlow.value = subMe
    }

    override fun makeReaction(query: List<ReactionQuery>) {
        viewModelScope.launch {
            makeReactionUseCase.invoke(query)
                .onSuccess { reaction ->
                    _state.update {
                        it.copy(
                            data = complexReactionMapper.map(reaction),
                            isShowProgress = false,
                            isSingleReaction = isSingleReaction,
                            isShowReactionInformation = true,
                        )
                    }
                }
                .onFailure { }
        }
    }

    override fun showShareDialog() {
        _state.update { it.copy(isShowShareDialog = true) }
    }

    override fun hideShareDialog() {
        _state.update { it.copy(isShowShareDialog = false) }
    }

    override fun shareAsSimpleText(isBaseMaterials: Boolean) {
        hideShareDialog()
        val text = sharedReactionMapper.getSimpleText(
            _state.value.data,
            isBaseMaterials,
        )
        viewModelScope.launch { _sideEffect.send(ReactorSideEffect.ShareReaction(text)) }
    }

    override fun shareAsEveNoteText(isBaseMaterials: Boolean) {
        hideShareDialog()
        val text = sharedReactionMapper.getRichText(
            _state.value.data,
            isBaseMaterials,
        )
        viewModelScope.launch { _sideEffect.send(ReactorSideEffect.ShareReaction(text)) }
    }

    override fun changeReactionInformationVisibility() {
        _state.update { it.copy(isShowReactionInformation = it.isShowReactionInformation.not()) }
    }

    override fun checkOfflineMode() {
        viewModelScope.launch {
            getPriceSourceUseCase.invoke()
                .onSuccess { source ->
                    _state.update { it.copy(isOfflineMode = source == PriceSource.OFFLINE) }
                }
        }
    }

    override fun disableOfflineMode() {
        viewModelScope.launch {
            updateOfflineModeSettingUseCase.invoke(false)
                .onSuccess { isOffline ->
                    _state.update { it.copy(isOfflineMode = isOffline) }
                    updatePriceAndStartReaction()
                }
        }
    }

    private fun updatePriceAndStartReaction() {
        viewModelScope.launch {

            _state.update { it.copy(isShowProgress = true) }
            val bpcIds = if (isSingleReaction) listOf(reactionId)
            else projectQuery.map { it.bpcId }

            updatePriceUseCase.invoke(bpcIds)
                .onSuccess {
                    launchReactor()
                }
                .onFailure {
                    launchReactor()
                    _sideEffect.send(ReactorSideEffect.PriceUpdateError)
                }
        }
    }

    private fun launchReactor() {
        if (isSingleReaction) {
            initTextWatchersFlows()
            makeReaction(query = listOf(singleQuery.value))
        } else makeReaction(projectQuery)
    }


    companion object {
        private const val INPUT_DEBOUNCE = 250L
    }

}