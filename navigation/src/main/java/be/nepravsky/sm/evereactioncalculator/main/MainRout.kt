package be.nepravsky.sm.evereactioncalculator.main

import androidx.compose.animation.scaleIn
import androidx.compose.animation.scaleOut
import androidx.compose.animation.togetherWith
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.navigation3.runtime.NavBackStack
import androidx.navigation3.runtime.NavKey
import androidx.navigation3.runtime.entryProvider
import androidx.navigation3.runtime.rememberNavBackStack
import androidx.navigation3.ui.NavDisplay
import be.nepravsky.sm.evereactioncalculator.AppDest
import be.nepravsky.sm.evereactioncalculator.core.SettingScreen
import be.nepravsky.sm.evereactioncalculator.library.LibraryRouterImpl
import be.nepravsky.sm.evereactioncalculator.library.LibraryScreen
import be.nepravsky.sm.evereactioncalculator.mainscreen.MainScreen
import be.nepravsky.sm.evereactioncalculator.reactions.ReactionsRouterImpl
import be.nepravsky.sm.evereactioncalculator.reactions.ReactionsScreen
import be.nepravsky.sm.evereactioncalculator.settings.SettingsRouterImpl

@Composable
fun MainRout(
    rootBackStack: NavBackStack<NavKey>
) {
    val backStack: NavBackStack<NavKey> = rememberNavBackStack(MainDest.Reactions)

    val mainRouter = remember {
        MainRouterImpl(
            onNavigateLibrary = { backStack.add(MainDest.Library) },
            onNavigateReactions = { backStack.add(MainDest.Reactions) },
            onNavigateSettings = { backStack.add(MainDest.Settings) },
        )
    }

    val libraryRouter = remember {
        LibraryRouterImpl(
            onNavigateAddProject = { projectId ->
                rootBackStack.add(AppDest.ProjectBuilder(projectId))
            },
            onNavigateEditProject = { projectId ->
                rootBackStack.add(AppDest.ProjectBuilder(projectId))
            },
            onNavigateRunProject = { projectId ->
                rootBackStack.add(AppDest.Reactor(projectId, false))
            },
        )
    }

    val reactionsRouter = remember {
        ReactionsRouterImpl(
            onNavigateSearchSettings = { rootBackStack.add(AppDest.SearchSettings) },
            onNavigateReactor = { id, isSingleReaction ->
                rootBackStack.add(AppDest.Reactor(id, isSingleReaction))
            },
        )
    }

    val settingsRouter = remember {
        SettingsRouterImpl(onNavigateAbout = { rootBackStack.add(AppDest.About) })
    }

    MainScreen(
        router = mainRouter
    ) {
        NavDisplay(
            backStack = backStack,
            onBack = { rootBackStack.removeLastOrNull() },
            transitionSpec = { scaleIn(initialScale = 0.95f) togetherWith scaleOut(targetScale = 0.95f) },
            entryProvider = entryProvider {
                entry<MainDest.Library> {
                    LibraryScreen(router = libraryRouter)
                }
                entry<MainDest.Reactions> {
                    ReactionsScreen(router = reactionsRouter)
                }
                entry<MainDest.Settings> {
                    SettingScreen(router = settingsRouter)
                }
            }
        )
    }
}