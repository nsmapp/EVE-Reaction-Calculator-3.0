package be.nepravsky.sm.evereactioncalculator

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import be.nepravsky.sm.evereactioncalculator.main.MainRouterImpl
import be.nepravsky.sm.evereactioncalculator.model.Tabs
import be.nepravsky.sm.evereactioncalculator.navigation.Rout
import be.nepravsky.sm.evereactioncalculator.projectbuilder.ProjectBuilderRouterImpl
import be.nepravsky.sm.evereactioncalculator.reaction.searchettings.SearchSettingsRouterImpl
import be.nepravsky.sm.evereactioncalculator.reactor.ReactorRouterImpl
import be.nepravsky.sm.evereactioncalculator.settings.about.AboutRouterImpl
import com.arkivanov.decompose.ComponentContext
import com.arkivanov.decompose.DefaultComponentContext
import com.arkivanov.decompose.extensions.compose.stack.Children
import com.arkivanov.decompose.extensions.compose.stack.animation.slide
import com.arkivanov.decompose.extensions.compose.stack.animation.stackAnimation
import com.arkivanov.decompose.router.stack.ChildStack
import com.arkivanov.decompose.router.stack.StackNavigation
import com.arkivanov.decompose.router.stack.childStack
import com.arkivanov.decompose.router.stack.pop
import com.arkivanov.decompose.router.stack.popTo
import com.arkivanov.decompose.router.stack.push
import com.arkivanov.decompose.value.Value
import kotlinx.serialization.Serializable
import org.koin.core.annotation.Factory
import org.koin.core.component.KoinComponent


@Factory(binds = [RootRouter::class])
class RootRouterImpl(
    componentContext: DefaultComponentContext
) : RootRouter, KoinComponent, ComponentContext by componentContext {

    private val navigation = StackNavigation<RootConfig>()

    override val stack: Value<ChildStack<*, RootChild>> =
        childStack(
            source = navigation,
            serializer = null,
            initialConfiguration = RootConfig.Main,
            handleBackButton = true,
            childFactory = ::child,
        )


    private fun child(rootConfig: RootConfig, componentContext: ComponentContext): RootChild =
        when (rootConfig) {
            is RootConfig.Main -> RootChild.MainRootChild(mainComponent(componentContext))
            is RootConfig.SearchSettings -> RootChild.SearchSettings(
                searchComponent(
                    componentContext
                )
            )

            is RootConfig.Reactor -> RootChild.ReactorChild(
                reactorComponent(
                    componentContext,
                    rootConfig.reactionId,
                    rootConfig.isSingeReaction
                )
            )

            is RootConfig.About -> RootChild.AboutChild(
                aboutComponent(
                    componentContext
                )
            )

            is RootConfig.ProjectBuilder -> RootChild.ProjectBuilderChild(
                projectBuilderComponent(
                    componentContext
                )

            )

        }

    private fun projectBuilderComponent(
        componentContext: ComponentContext
    ): ProjectBuilderRouterImpl =
        ProjectBuilderRouterImpl(
            componentContext = componentContext,
            onOpenSearchSettings = { navigation.push(RootConfig.SearchSettings) },
            onBackPressed = { navigation.pop() }
        )

    private fun aboutComponent(componentContext: ComponentContext): AboutRouterImpl =
        AboutRouterImpl(
            componentContext = componentContext,
            onBackPressed = { navigation.pop() }
        )

    private fun searchComponent(componentContext: ComponentContext): SearchSettingsRouterImpl =
        SearchSettingsRouterImpl(
            componentContext = componentContext,
            onBackPressed = { navigation.pop() }
        )

    private fun mainComponent(componentContext: ComponentContext): MainRouterImpl =
        MainRouterImpl(
            tab = Tabs.REACTIONS,
            onSearchSettings = {
                navigation.push(RootConfig.SearchSettings)
            },
            onOpenAboutScreen = {
                navigation.push(RootConfig.About)
            },
            onReaction = { reactionId, isSingleReaction ->
                navigation.push(RootConfig.Reactor(reactionId, isSingleReaction))
            },
            onAddProject = {
                navigation.push(RootConfig.ProjectBuilder)
            },
            componentContext = componentContext
        )

    private fun reactorComponent(
        componentContext: ComponentContext,
        reactionId: Long,
        isSingeReaction: Boolean
    ): ReactorRouterImpl =
        ReactorRouterImpl(
            reactionId = reactionId,
            isSingleReaction = isSingeReaction,
            componentContext = componentContext
        )


    override fun onBackClicked(toIndex: Int) {
        navigation.popTo(toIndex)
    }

    @Composable
    override fun Content() {
        Children(
            modifier = Modifier.fillMaxSize(),
            stack = stack,
            animation = stackAnimation(slide())
        ) { child ->
            child.instance.rout.Content()
        }
    }

    init {
//        lifecycle... // Access the Lifecycle
//        stateKeeper... // Access the StateKeeper
//        instanceKeeper... // Access the InstanceKeeper
//        backHandler... // Access the BackHandler
    }
}


sealed interface RootChild {

    val rout: Rout

    class MainRootChild(override val rout: MainRouterImpl) : RootChild
    class SearchSettings(override val rout: SearchSettingsRouterImpl) : RootChild
    class ReactorChild(override val rout: ReactorRouterImpl) : RootChild
    class AboutChild(override val rout: AboutRouterImpl) : RootChild
    class ProjectBuilderChild(override val rout: ProjectBuilderRouterImpl) : RootChild

}

@Serializable
sealed interface RootConfig {

    data object Main : RootConfig
    data object SearchSettings : RootConfig
    data class Reactor(val reactionId: Long, val isSingeReaction: Boolean) : RootConfig
    data object About : RootConfig
    data object ProjectBuilder : RootConfig
}