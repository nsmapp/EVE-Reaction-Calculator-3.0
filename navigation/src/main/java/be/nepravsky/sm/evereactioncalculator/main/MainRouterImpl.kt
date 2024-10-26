package be.nepravsky.sm.evereactioncalculator.main

import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.lifecycle.ViewModelProvider
import be.nepravsky.sm.evereactioncalculator.MainRouter
import be.nepravsky.sm.evereactioncalculator.MainScreen
import be.nepravsky.sm.evereactioncalculator.MainViewModel
import be.nepravsky.sm.evereactioncalculator.library.LibraryRouterImpl
import be.nepravsky.sm.evereactioncalculator.model.Tabs
import be.nepravsky.sm.evereactioncalculator.navigation.Rout
import be.nepravsky.sm.evereactioncalculator.reaction.ReactionRouterImpl
import be.nepravsky.sm.evereactioncalculator.settings.SettingsRouterImpl
import be.nepravsky.sm.evereactioncalculator.viewmodel.KoinAssistedViewModelFactory
import be.nepravsky.sm.evereactioncalculator.viewmodel.viewModelKey
import com.arkivanov.decompose.ComponentContext
import com.arkivanov.decompose.extensions.compose.stack.Children
import com.arkivanov.decompose.extensions.compose.stack.animation.fade
import com.arkivanov.decompose.extensions.compose.stack.animation.plus
import com.arkivanov.decompose.extensions.compose.stack.animation.scale
import com.arkivanov.decompose.extensions.compose.stack.animation.stackAnimation
import com.arkivanov.decompose.router.stack.ChildStack
import com.arkivanov.decompose.router.stack.StackNavigation
import com.arkivanov.decompose.router.stack.bringToFront
import com.arkivanov.decompose.router.stack.childStack
import com.arkivanov.decompose.router.stack.popTo
import com.arkivanov.decompose.value.Value
import kotlinx.serialization.Serializable
import org.koin.core.parameter.parametersOf

class MainRouterImpl(
    tab: Tabs,
    private val onSearchSettings: () -> Unit,
    private val onOpenAboutScreen: () -> Unit,
    private val onReaction: (reactionId: Long, isSingleReaction: Boolean) -> Unit,
    private val onAddProject: (projectId: Long?) -> Unit,
    private val onRunProject: (projectId: Long) -> Unit,
    componentContext: ComponentContext
) : Rout(
    componentContext = componentContext,
    viewModelKey = MainViewModel::class.viewModelKey(),
), MainRouter {

    private val navigation = StackNavigation<MainDestination>()

    val stack: Value<ChildStack<*, MainChild>> = childStack(
        source = navigation,
        serializer = null,
        initialConfiguration = MainDestination.Reactions,
        handleBackButton = false,
        childFactory = ::child,
    )

    override val viewModelFactory: ViewModelProvider.Factory by lazy {
        KoinAssistedViewModelFactory(
            parametersOf(tab)
        )
    }


    private fun child(
        mainDestination: MainDestination,
        componentContext: ComponentContext
    ): MainChild =
        when (mainDestination) {
            is MainDestination.Library -> libraryComponent(componentContext)
            is MainDestination.Reactions -> reactionsComponent(componentContext)
            is MainDestination.Settings -> settingsComponent(componentContext)
        }

    private fun settingsComponent(componentContext: ComponentContext): MainChild =
        MainChild.SettingsChild(
            SettingsRouterImpl(
                componentContext,
                onOpenAboutScreen,
            )
        )

    private fun reactionsComponent(componentContext: ComponentContext): MainChild =
        MainChild.ReactionsChild(
            ReactionRouterImpl(
                onSearchSettings = onSearchSettings,
                onReaction = onReaction,
                componentContext = componentContext,
            )
        )

    private fun libraryComponent(componentContext: ComponentContext): MainChild =
        MainChild.LibraryChild(
            LibraryRouterImpl(
                componentContext = componentContext,
                onAddProject = { projectId -> onAddProject(projectId) },
                onRunProject = { projectId -> onRunProject(projectId) }
            )
        )


    override fun selectTab(tab: Tabs) {
        when (tab) {
            Tabs.LIBRARY -> navigation.bringToFront(MainDestination.Library)
            Tabs.REACTIONS -> navigation.bringToFront(MainDestination.Reactions)
            Tabs.SETTINGS -> navigation.bringToFront(MainDestination.Settings)
        }
    }

    override fun onBackClicked(toIndex: Int) {
        navigation.popTo(toIndex)
    }

    @Composable
    override fun Content() {
        MainScreen(
            viewModel = decomposeViewModel(),
            router = this
        ) {
            Children(
                modifier = Modifier.wrapContentSize(),
                stack = stack,
                animation = stackAnimation(fade() + scale()),
            ) {
                it.instance.rout.Content()
            }
        }
    }

}

sealed interface MainChild {

    val rout: Rout

    class LibraryChild(override val rout: LibraryRouterImpl) : MainChild
    class ReactionsChild(override val rout: ReactionRouterImpl) : MainChild
    class SettingsChild(override val rout: SettingsRouterImpl) : MainChild
}


@Serializable
sealed interface MainDestination {

    data object Library : MainDestination
    data object Reactions : MainDestination
    data object Settings : MainDestination
}


