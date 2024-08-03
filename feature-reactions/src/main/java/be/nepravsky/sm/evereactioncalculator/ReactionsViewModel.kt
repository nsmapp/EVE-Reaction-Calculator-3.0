package be.nepravsky.sm.evereactioncalculator

import androidx.lifecycle.viewModelScope
import be.nepravsky.sm.domain.model.query.ReactionsQuery
import be.nepravsky.sm.domain.usecase.GetActiveGroupIdsUseCase
import be.nepravsky.sm.domain.usecase.GetBpcListUseCase
import be.nepravsky.sm.evereactioncalculator.contract.ReactionsContract
import be.nepravsky.sm.evereactioncalculator.model.ReactionsState
import be.nepravsky.sm.evereactioncalculator.utils.TEXT_EMPTY
import be.nepravsky.sm.evereactioncalculator.viewmodel.BaseViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import org.koin.core.annotation.Factory


@Factory(binds = [BaseViewModel::class])
class ReactionsViewModel(
    private val getBpcListUseCase: GetBpcListUseCase,
    private val getActiveGroupIdsUseCase: GetActiveGroupIdsUseCase
) : BaseViewModel(), ReactionsContract {

    private val _state = MutableStateFlow<ReactionsState>(ReactionsState.EMPTY)
    val state = _state.asStateFlow()

    private val _activeGroupIds = MutableStateFlow<List<Long>>(emptyList())
    private var searchQuery = TEXT_EMPTY



    init {
        getActiveReactionGroupIds()
    }

    override fun getBpcList(query: String) {

        searchQuery = query
        viewModelScope.launch {
            getBpcListUseCase.invoke(ReactionsQuery(query, _activeGroupIds.value))
                .onSuccess { bpcList ->
                    _state.update { it.copy(bpcShortList = bpcList) }
                }
                .onFailure {
                    TODO("getBpcListUseCase don't implemented")
                }
        }
    }

    override fun getActiveReactionGroupIds() {
        viewModelScope.launch {
            getActiveGroupIdsUseCase.invoke()
                .collectLatest { ids ->
                    _activeGroupIds.update { ids }
                    getBpcList(searchQuery)
                }
        }
    }

    override fun openFilter() {
        TODO("Not yet implemented")
    }
}