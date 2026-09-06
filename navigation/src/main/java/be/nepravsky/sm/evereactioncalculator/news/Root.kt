package be.nepravsky.sm.evereactioncalculator.news

import androidx.compose.animation.ContentTransform
import androidx.compose.animation.core.tween
import androidx.compose.animation.slideInHorizontally
import androidx.compose.animation.slideOutHorizontally
import androidx.compose.animation.togetherWith
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.asPaddingValues
import androidx.compose.foundation.layout.ime
import androidx.compose.foundation.layout.imePadding
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.filled.PlayArrow
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemColors
import androidx.compose.material3.NavigationBarItemDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation3.runtime.NavKey
import androidx.navigation3.runtime.entryProvider
import androidx.navigation3.runtime.rememberNavBackStack
import androidx.navigation3.ui.NavDisplay
import be.nepravsky.sm.evereactioncalculator.news.about.about
import be.nepravsky.sm.evereactioncalculator.news.library.LibraryDest
import be.nepravsky.sm.evereactioncalculator.news.library.library
import be.nepravsky.sm.evereactioncalculator.news.projectbuilder.projectBuilder
import be.nepravsky.sm.evereactioncalculator.news.reactions.ReactionsDest
import be.nepravsky.sm.evereactioncalculator.news.reactions.reaction
import be.nepravsky.sm.evereactioncalculator.news.reactor.reactor
import be.nepravsky.sm.evereactioncalculator.news.searchsettings.searchSettings
import be.nepravsky.sm.evereactioncalculator.news.settings.SettingsDest
import be.nepravsky.sm.evereactioncalculator.news.settings.settings
import be.nepravsky.sm.uikit.theme.AppTheme
import be.nepravsky.sm.uikit.view.icons.SmallIcon

@Composable
fun Root(
    radioTrack: String? = null,
    singleAudioTrack: String? = null,
) {

    val backStack = rememberNavBackStack(ReactionsDest.root())
    val navigator = remember { Navigator(backStack) }

    val isKeyboardVisible = WindowInsets.ime.asPaddingValues().calculateBottomPadding() > 0.dp

    val entries = remember(navigator){ getEntries(navigator)}

    Scaffold(
        containerColor = AppTheme.colors.foreground_hard,
        bottomBar = {
            NavigationBar(
                containerColor = AppTheme.colors.foreground_hard,
                contentColor = AppTheme.colors.foreground_hard,
            ) {
                NavigationBarItem(
                    selected = navigator.currentScreen is LibraryDest,
                    onClick = { navigator.navigateTop(LibraryDest.root()) },
                    icon = { SmallIcon(imageVector = Icons.Default.Menu) },
                    colors = navigationBarItemColors,
                )

                NavigationBarItem(
                    selected = navigator.currentScreen is ReactionsDest,
                    onClick = { navigator.navigateSingleTop(ReactionsDest.root())},
                    icon = { SmallIcon(imageVector = Icons.Default.PlayArrow) },
                    colors = navigationBarItemColors,
                )

                NavigationBarItem(
                    selected = navigator.currentScreen is SettingsDest,
                    onClick = { navigator.navigateTop(SettingsDest.root())},
                    icon = { SmallIcon(imageVector = Icons.Default.Settings) },
                    colors = navigationBarItemColors,
                )
            }
        }
    ) { padding ->
        NavDisplay(
            modifier = Modifier
                .padding(padding)
                .imePadding(),
            transitionSpec = { transformSlideOpen() },
            popTransitionSpec = { transformPop() },
            predictivePopTransitionSpec = { transformPredicativePop() },
            backStack = backStack,
            onBack = navigator::navigateBack,
            entryProvider = entries
        )
    }
}

val navigationBarItemColors: NavigationBarItemColors
    @Composable
    get() = NavigationBarItemDefaults.colors().copy(selectedIndicatorColor = AppTheme.colors.accent)

fun transformPredicativePop(): ContentTransform = slideInHorizontally(
    initialOffsetX = { -it },
    animationSpec = tween(150)
) togetherWith slideOutHorizontally(
    targetOffsetX = { it },
    animationSpec = tween(150)
)

fun transformPop(): ContentTransform = slideInHorizontally(
    initialOffsetX = { -it },
    animationSpec = tween(350)
) togetherWith slideOutHorizontally(
    targetOffsetX = { it },
    animationSpec = tween(350)
)

fun transformSlideOpen(): ContentTransform = slideInHorizontally(
    initialOffsetX = { it },
    animationSpec = tween(350)
) togetherWith slideOutHorizontally(
    targetOffsetX = { -it },
    animationSpec = tween(350)
)

fun getEntries(navigator: Navigator) = entryProvider<NavKey> {
    library(navigator)
    reaction(navigator)
    settings(navigator)

    projectBuilder(navigator)
    reactor(navigator)
    searchSettings(navigator)

    about(navigator)
}
