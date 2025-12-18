package be.nepravsky.sm.evereactioncalculator.settings

import be.nepravsky.sm.evereactioncalculator.core.SettingsRouter

class SettingsRouterImpl(
    private val onNavigateAbout: (() -> Unit),
) : SettingsRouter {

    override fun openAboutScreen() {
        onNavigateAbout()
    }
}