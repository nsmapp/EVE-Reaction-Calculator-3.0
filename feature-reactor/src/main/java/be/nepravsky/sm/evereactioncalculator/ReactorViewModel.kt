package be.nepravsky.sm.evereactioncalculator

import androidx.lifecycle.viewModelScope
import be.nepravsky.sm.domain.usecase.price.UpdatePriceUseCase
import be.nepravsky.sm.evereactioncalculator.viewmodel.BaseViewModel
import kotlinx.coroutines.launch
import org.koin.core.annotation.Factory
import org.koin.core.annotation.InjectedParam

@Factory(binds = [BaseViewModel::class])
class ReactorViewModel(
    @InjectedParam val reactionId: Long,
    private val updatePriceUseCase: UpdatePriceUseCase,
): BaseViewModel() {


    init {
        viewModelScope.launch {
            updatePriceUseCase.invoke(reactionId)
                .onSuccess { }
                .onFailure {  }
        }
    }

}