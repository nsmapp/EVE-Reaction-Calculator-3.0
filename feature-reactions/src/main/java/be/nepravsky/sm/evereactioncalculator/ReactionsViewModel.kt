package be.nepravsky.sm.evereactioncalculator

import androidx.lifecycle.viewModelScope
import be.nepravsky.sm.domain.query.ReactionsQuery
import be.nepravsky.sm.domain.usecase.GetBpcListUseCase
import be.nepravsky.sm.evereactioncalculator.contract.ReactionsContract
import be.nepravsky.sm.evereactioncalculator.model.ReactionsState
import be.nepravsky.sm.evereactioncalculator.viewmodel.BaseViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import org.koin.core.annotation.Factory


@Factory(binds = [BaseViewModel::class])
class ReactionsViewModel(
    private val getBpcListUseCase: GetBpcListUseCase
) : BaseViewModel(), ReactionsContract {

    private val _state = MutableStateFlow<ReactionsState>(ReactionsState.EMPTY)
    val state = _state.asStateFlow()


    override fun getBpcList() {
        viewModelScope.launch {
            getBpcListUseCase.invoke(ReactionsQuery("", listOf()))
                .onSuccess { bpcList ->
                    _state.update { it.copy(bpcList = bpcList) }
                }
                .onFailure {
                    TODO("getBpcListUseCase don't implemented")
                }
        }
    }

    override fun openFilter() {
        TODO("Not yet implemented")
    }
}