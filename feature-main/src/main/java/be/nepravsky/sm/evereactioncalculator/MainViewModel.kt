package be.nepravsky.sm.evereactioncalculator

import be.nepravsky.sm.evereactioncalculator.viewmodel.BaseViewModel
import org.koin.core.annotation.Factory

@Factory(binds = [BaseViewModel::class])
class MainViewModel: BaseViewModel() {
}