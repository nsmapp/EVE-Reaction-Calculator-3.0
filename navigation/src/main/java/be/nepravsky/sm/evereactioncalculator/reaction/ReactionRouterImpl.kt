package be.nepravsky.sm.evereactioncalculator.reaction

import androidx.compose.runtime.Composable
import be.nepravsky.sm.evereactioncalculator.reactions.contract.ReactionsRouter
import be.nepravsky.sm.evereactioncalculator.reactions.ReactionsScreen
import be.nepravsky.sm.evereactioncalculator.reactions.ReactionsViewModel
import be.nepravsky.sm.evereactioncalculator.navigation.Rout
import be.nepravsky.sm.evereactioncalculator.viewmodel.viewModelKey
import com.arkivanov.decompose.ComponentContext

class ReactionRouterImpl(
    private val onSearchSettings: () -> Unit,
    private val onReaction: (Long) -> Unit,
    componentContext: ComponentContext,
) : Rout(
    componentContext = componentContext,
    viewModelKey = ReactionsViewModel::class.viewModelKey()
), ReactionsRouter {

    @Composable
    override fun Content() {
        ReactionsScreen(
            viewModel = decomposeViewModel(),
            router = this
        )
    }

    override fun openSearchSettings() {
        onSearchSettings.invoke()
    }

    override fun openReaction(id: Long) {
        onReaction.invoke(id)
    }
}