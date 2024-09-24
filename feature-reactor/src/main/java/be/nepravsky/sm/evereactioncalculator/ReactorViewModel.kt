package be.nepravsky.sm.evereactioncalculator

import androidx.lifecycle.viewModelScope
import be.nepravsky.sm.domain.model.query.ReactionQuery
import be.nepravsky.sm.domain.usecase.price.UpdatePriceUseCase
import be.nepravsky.sm.domain.usecase.reactor.MakeReactionUseCase
import be.nepravsky.sm.evereactioncalculator.mapper.ComplexReactionMapper
import be.nepravsky.sm.evereactioncalculator.model.ReactorState
import be.nepravsky.sm.evereactioncalculator.viewmodel.BaseViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import org.koin.core.annotation.Factory
import org.koin.core.annotation.InjectedParam

@Factory(binds = [BaseViewModel::class])
class ReactorViewModel(
    @InjectedParam val reactionId: Long,
    private val updatePriceUseCase: UpdatePriceUseCase,
    private val makeReactionUseCase: MakeReactionUseCase,
    private val complexReactionMapper: ComplexReactionMapper,
): BaseViewModel() {

    private val _state = MutableStateFlow(ReactorState.EMPTY)
    val state = _state.asStateFlow()


    init {
        viewModelScope.launch {
            updatePriceUseCase.invoke(reactionId)
                .onSuccess {
                    makeReaction(query = listOf(ReactionQuery(reactionId)))
                }
                .onFailure {
                    makeReaction(query = listOf(ReactionQuery(reactionId)))
                }
        }
    }

    fun makeReaction(query: List<ReactionQuery>){
        viewModelScope.launch {
            makeReactionUseCase.invoke(query)
                .onSuccess { reaction ->
                    _state.update { it.copy(data = complexReactionMapper.map(reaction)) }
                }
                .onFailure {  }
        }
    }

}