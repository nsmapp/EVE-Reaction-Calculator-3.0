package be.nepravsky.sm.evereactioncalculator.news.settings

import androidx.navigation3.runtime.EntryProviderScope
import androidx.navigation3.runtime.NavKey
import be.nepravsky.sm.evereactioncalculator.core.SettingScreen
import be.nepravsky.sm.evereactioncalculator.news.Navigator
import be.nepravsky.sm.evereactioncalculator.news.about.AboutDest

fun EntryProviderScope<NavKey>.settings(navigator: Navigator) {

    entry<SettingsDest.Settings> {
        SettingScreen(
            onOpenAboutScreen = { navigator.navigate(AboutDest.root()) }
        )
    }
}