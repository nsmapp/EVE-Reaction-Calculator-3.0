package be.nepravsky.sm.evereactioncalculator.reaction

import androidx.compose.runtime.Composable
import be.nepravsky.sm.evereactioncalculator.contract.ReactionRouter
import be.nepravsky.sm.evereactioncalculator.ReactionsScreen
import be.nepravsky.sm.evereactioncalculator.ReactionsViewModel
import be.nepravsky.sm.evereactioncalculator.RootConfig
import be.nepravsky.sm.evereactioncalculator.navigation.Rout
import be.nepravsky.sm.evereactioncalculator.viewmodel.viewModelKey
import com.arkivanov.decompose.ComponentContext
import com.arkivanov.decompose.router.stack.StackNavigator
import com.arkivanov.decompose.router.stack.push

class ReactionRouterImpl(
    private val stackNavigation: StackNavigator<RootConfig>,
    componentContext: ComponentContext,
) : Rout(
    componentContext = componentContext,
    viewModelKey = ReactionsViewModel::class.viewModelKey()
), ReactionRouter {

    @Composable
    override fun Content() {
        ReactionsScreen(
            onReactionClick = {
                stackNavigation.push(RootConfig.Reactor)
            },
            viewModel = decomposeViewModel()
        )
    }
}