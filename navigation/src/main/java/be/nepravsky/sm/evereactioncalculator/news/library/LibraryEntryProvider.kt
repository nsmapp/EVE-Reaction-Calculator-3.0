package be.nepravsky.sm.evereactioncalculator.news.library

import androidx.navigation3.runtime.EntryProviderScope
import androidx.navigation3.runtime.NavKey
import be.nepravsky.sm.evereactioncalculator.library.LibraryScreen
import be.nepravsky.sm.evereactioncalculator.news.Navigator
import be.nepravsky.sm.evereactioncalculator.news.projectbuilder.ProjectBuilderDest
import be.nepravsky.sm.evereactioncalculator.news.reactor.ReactorDest

fun EntryProviderScope<NavKey>.library(navigator: Navigator) {

    entry<LibraryDest.Library> {
        LibraryScreen(
            onAddProject = { projectId -> navigator.navigate(ProjectBuilderDest.root(projectId))},
            onEditProject = { projectId -> navigator.navigate(ProjectBuilderDest.root(projectId))},
            onRunProject = { projectId -> navigator.navigate(ReactorDest.root(projectId, false)) }
        )
    }
}