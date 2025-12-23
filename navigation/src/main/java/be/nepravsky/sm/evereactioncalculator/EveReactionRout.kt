package be.nepravsky.sm.evereactioncalculator

import androidx.compose.animation.core.tween
import androidx.compose.animation.slideInHorizontally
import androidx.compose.animation.slideOutHorizontally
import androidx.compose.animation.togetherWith
import androidx.compose.runtime.Composable
import androidx.navigation3.runtime.NavBackStack
import androidx.navigation3.runtime.NavKey
import androidx.navigation3.runtime.entryProvider
import androidx.navigation3.runtime.rememberNavBackStack
import androidx.navigation3.ui.NavDisplay
import be.nepravsky.builder.BuilderScreen
import be.nepravsky.searchsettings.SearchSettingsScreen
import be.nepravsky.sm.evereactioncalculator.about.AboutRouterImpl
import be.nepravsky.sm.evereactioncalculator.about.AboutScreen
import be.nepravsky.sm.evereactioncalculator.main.MainRout
import be.nepravsky.sm.evereactioncalculator.projectbuilder.ProjectBuilderRouterImpl
import be.nepravsky.sm.evereactioncalculator.reactor.ReactorRouterImpl
import be.nepravsky.sm.evereactioncalculator.searchsettings.SearchSettingsRouterImpl


@Composable
fun EveReactionRout() {

    val rootBackStack: NavBackStack<NavKey> =
        rememberNavBackStack(AppDest.Root)


    NavDisplay(
        backStack = rootBackStack,
        onBack = { rootBackStack.removeLastOrNull() },
        transitionSpec = {
            slideInHorizontally(
                initialOffsetX = { it },
                animationSpec = tween(250)
            ) togetherWith slideOutHorizontally(
                targetOffsetX = { -it },
                animationSpec = tween(250)
            )
        },
        popTransitionSpec = {
            slideInHorizontally(
                initialOffsetX = { -it },
                animationSpec = tween(250)
            ) togetherWith slideOutHorizontally(
                targetOffsetX = { it },
                animationSpec = tween(250)
            )
        },
        predictivePopTransitionSpec = {
            slideInHorizontally(
                initialOffsetX = { -it },
                animationSpec = tween(250)
            ) togetherWith slideOutHorizontally(
                targetOffsetX = { it },
                animationSpec = tween(250)
            )
        },
        entryProvider = entryProvider {
            entry<AppDest.Root> {
                MainRout(rootBackStack)
            }
            entry<AppDest.SearchSettings> {
                SearchSettingsScreen(
                    router = SearchSettingsRouterImpl(
                    onNavigateBack = { rootBackStack.removeLastOrNull() }
                ))
            }
            entry<AppDest.Reactor> { data ->
                ReactorScreen(
                    reactionId = data.id,
                    isSingleReaction = data.isSingleReaction,
                    router = ReactorRouterImpl(),
                )
            }
            entry<AppDest.ProjectBuilder> {
                BuilderScreen(
                    projectId = it.projectId,
                    router = ProjectBuilderRouterImpl(
                        onNavigateSearchSettings = { rootBackStack.add(AppDest.SearchSettings) },
                        onNavigateBack = { rootBackStack.removeLastOrNull() }
                    ),
                )
            }
            entry<AppDest.About> {
                AboutScreen(
                    router = AboutRouterImpl(
                        onNavigationBack = { rootBackStack.removeLastOrNull() }
                    )
                )
            }
        }
    )
}
