package be.nepravsky.sm.evereactioncalculator.mainscreen

import be.nepravsky.sm.evereactioncalculator.mainscreen.model.Tabs



interface MainRouter {

    fun selectTab(tab: Tabs)

    fun navigationBack(toIndex: Int)
}
