package be.nepravsky.sm.evereactioncalculator

import androidx.compose.runtime.Composable
import be.nepravsky.sm.uikit.view.text.TextBold

@Composable
fun ReactorScreen(
    viewModel: ReactorViewModel,
    router: ReactorRouter,
){
    TextBold(text = "Reactor screen: ${viewModel.reactionId}")
}