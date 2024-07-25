package be.nepravsky.sm.evereactioncalculator

import be.nepravsky.sm.evereactioncalculator.RootRouter
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject

class InitialDepsHolderAndroid : InitialDepsHolder, KoinComponent {

    override val rootNavGraph: RootRouter by inject()
}