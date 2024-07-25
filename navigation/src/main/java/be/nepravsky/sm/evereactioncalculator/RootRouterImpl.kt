package be.nepravsky.sm.evereactioncalculator

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import be.nepravsky.sm.evereactioncalculator.main.MainRouterImpl
import be.nepravsky.sm.evereactioncalculator.model.Tabs
import be.nepravsky.sm.evereactioncalculator.navigation.Rout
import be.nepravsky.sm.evereactioncalculator.reactor.ReactorRouterImpl
import com.arkivanov.decompose.ComponentContext
import com.arkivanov.decompose.DefaultComponentContext
import com.arkivanov.decompose.extensions.compose.stack.Children
import com.arkivanov.decompose.extensions.compose.stack.animation.fade
import com.arkivanov.decompose.extensions.compose.stack.animation.stackAnimation
import com.arkivanov.decompose.router.stack.ChildStack
import com.arkivanov.decompose.router.stack.StackNavigation
import com.arkivanov.decompose.router.stack.childStack
import com.arkivanov.decompose.router.stack.popTo
import com.arkivanov.decompose.value.Value
import kotlinx.serialization.Serializable
import org.koin.core.annotation.Factory
import org.koin.core.component.KoinComponent


@Factory(binds = [RootRouter::class])
class RootRouterImpl(
    componentContext: DefaultComponentContext
): RootRouter, KoinComponent, ComponentContext by componentContext {

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
        when(rootConfig){
            is RootConfig.Main -> RootChild.MainRootChild(mainComponent(componentContext))
            is RootConfig.Reactor -> RootChild.ReactorChild(rectorComponent(componentContext))
        }

    private fun mainComponent(componentContext: ComponentContext): MainRouterImpl =
        MainRouterImpl(
            tab = Tabs.REACTIONS,
            stackNavigation = navigation,
            componentContext = componentContext
        )

    private fun rectorComponent(componentContext: ComponentContext): ReactorRouterImpl =
        ReactorRouterImpl(
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
            animation = stackAnimation(fade())
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


sealed interface RootChild{

    val rout: Rout

    class MainRootChild(override val rout: MainRouterImpl): RootChild
    class ReactorChild(override val rout: ReactorRouterImpl): RootChild

}

@Serializable
sealed interface RootConfig{

    data object Main: RootConfig
    data object Reactor: RootConfig
}