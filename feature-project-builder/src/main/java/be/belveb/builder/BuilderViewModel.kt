package be.belveb.builder

import be.nepravsky.sm.evereactioncalculator.viewmodel.BaseViewModel
import org.koin.core.annotation.Factory

@Factory(binds = [BaseViewModel::class])
class BuilderViewModel: BaseViewModel(), BuilderContract {
}