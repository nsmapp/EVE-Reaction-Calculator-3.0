package be.belveb.builder

import androidx.compose.runtime.Composable
import be.nepravsky.sm.uikit.view.text.TextBold

@Composable
fun BuilderScreen(
    viewModel: BuilderViewModel,
    router: BuilderRouter
) {

    TextBold(text = "Builder screen")
}