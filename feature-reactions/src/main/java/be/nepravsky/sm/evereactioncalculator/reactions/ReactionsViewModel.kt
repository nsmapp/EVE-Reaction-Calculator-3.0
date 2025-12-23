package be.nepravsky.sm.evereactioncalculator.reactions

import androidx.lifecycle.viewModelScope
import be.nepravsky.sm.domain.model.query.ReactionsQuery
import be.nepravsky.sm.domain.usecase.GetBpcListUseCase
import be.nepravsky.sm.evereactioncalculator.reactions.contract.ReactionsContract
import be.nepravsky.sm.evereactioncalculator.reactions.mappers.BpcShortModelMapper
import be.nepravsky.sm.evereactioncalculator.reactions.model.ReactionsState
import be.nepravsky.sm.evereactioncalculator.utils.TEXT_EMPTY
import be.nepravsky.sm.evereactioncalculator.viewmodel.BaseViewModel
import kotlinx.coroutines.FlowPreview
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.debounce
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import org.koin.core.annotation.Factory


@OptIn(FlowPreview::class)
@Factory
class ReactionsViewModel(
    private val getBpcListUseCase: GetBpcListUseCase,
    private val bpcShortModelMapper: BpcShortModelMapper,
) : BaseViewModel(), ReactionsContract {

    private val _state = MutableStateFlow<ReactionsState>(ReactionsState.EMPTY)
    val state = _state.asStateFlow()
    private val _searchQuery = MutableStateFlow(TEXT_EMPTY)

    init {
        viewModelScope.launch {
            _searchQuery
                .debounce(500)
                .collectLatest { query ->
                getReactions(query)
            }
        }
    }

    override fun onScreeOpen() {
        getReactions(_searchQuery.value)
    }

    override fun searchReactions(query: String) {
        _searchQuery.update { query }
        _state.update { it.copy(searchText = query) }
    }

    private fun getReactions(query: String) {
        viewModelScope.launch {
            getBpcListUseCase.invoke(ReactionsQuery(query))
                .onSuccess { bpcList ->
                    _state.update {
                        it.copy(bpcShortList = bpcShortModelMapper.map(bpcList))
                    }
                }
                .onFailure {
                    TODO("getBpcListUseCase don't implemented")
                }
        }
    }
}