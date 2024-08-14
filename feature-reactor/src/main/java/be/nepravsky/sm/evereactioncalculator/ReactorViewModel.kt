package be.nepravsky.sm.evereactioncalculator

import be.nepravsky.sm.evereactioncalculator.viewmodel.BaseViewModel
import org.koin.core.annotation.Factory
import org.koin.core.annotation.InjectedParam

@Factory(binds = [BaseViewModel::class])
class ReactorViewModel(
    @InjectedParam val reactionId: Long,
): BaseViewModel() {

}