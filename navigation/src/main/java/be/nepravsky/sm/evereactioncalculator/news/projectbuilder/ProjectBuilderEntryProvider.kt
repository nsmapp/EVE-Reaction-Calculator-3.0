package be.nepravsky.sm.evereactioncalculator.news.projectbuilder

import androidx.navigation3.runtime.EntryProviderScope
import androidx.navigation3.runtime.NavKey
import be.nepravsky.builder.BuilderScreen
import be.nepravsky.sm.evereactioncalculator.news.Navigator
import be.nepravsky.sm.evereactioncalculator.news.searchsettings.SearchSettingsDest

fun EntryProviderScope<NavKey>.projectBuilder(navigator: Navigator){
    entry<ProjectBuilderDest.ProjectBuilder> { data ->
        BuilderScreen(
            projectId = data.projectId,
            onBackPressed = { navigator.navigateBack() },
            onSearchSettings = {navigator.navigate(SearchSettingsDest.root())}
        )
    }
}