package be.nepravsky.sm.evereactioncalculator.news.searchsettings

import androidx.navigation3.runtime.EntryProviderScope
import androidx.navigation3.runtime.NavKey
import be.nepravsky.searchsettings.SearchSettingsScreen
import be.nepravsky.sm.evereactioncalculator.news.Navigator

fun EntryProviderScope<NavKey>.searchSettings(navigator: Navigator) {
    entry<SearchSettingsDest.SearchSettings> {
        SearchSettingsScreen(
            onNavigateBack = { navigator.navigateBack() }
        )
    }
}