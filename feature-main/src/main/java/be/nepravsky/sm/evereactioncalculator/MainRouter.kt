package be.nepravsky.sm.evereactioncalculator

import be.nepravsky.sm.evereactioncalculator.model.Tabs



interface MainRouter {

    fun selectTab(tab: Tabs)

    fun onBackClicked(toIndex: Int)



}
