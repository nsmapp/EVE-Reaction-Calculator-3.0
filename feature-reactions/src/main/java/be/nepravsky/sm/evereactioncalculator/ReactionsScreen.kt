package be.nepravsky.sm.evereactioncalculator

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import be.nepravsky.sm.uikit.view.text.TextBold


@Composable
fun ReactionsScreen(
    onReactionClick: () -> Unit,
    viewModel: ReactionsViewModel,
){

    val state = viewModel.state.collectAsState()
    LaunchedEffect(null) {
        viewModel.getBpcList()
    }

    Column(modifier = Modifier.fillMaxSize()){
        TextBold(modifier = Modifier, text = state.value.bpcList.size.toString())
    }
}