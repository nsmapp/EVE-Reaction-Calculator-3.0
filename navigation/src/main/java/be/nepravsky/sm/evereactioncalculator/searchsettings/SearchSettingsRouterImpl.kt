package be.nepravsky.sm.evereactioncalculator.searchsettings

import be.nepravsky.searchsettings.contract.SearchSettingsRouter

class SearchSettingsRouterImpl(
    private val onNavigateBack: (() -> Unit)
) : SearchSettingsRouter {

    override fun navigateBack() {
        onNavigateBack()
    }
}