package be.nepravsky.sm.evereactioncalculator.main

import be.nepravsky.sm.evereactioncalculator.mainscreen.MainRouter
import be.nepravsky.sm.evereactioncalculator.mainscreen.model.Tabs

class MainRouterImpl(
    private val onNavigateLibrary: (() -> Unit),
    private val onNavigateReactions: (() -> Unit),
    private val onNavigateSettings: (() -> Unit),
) : MainRouter {
    override fun selectTab(tab: Tabs) {
        when (tab) {
            Tabs.LIBRARY -> onNavigateLibrary()
            Tabs.REACTIONS -> onNavigateReactions()
            Tabs.SETTINGS -> onNavigateSettings()
        }
    }
}