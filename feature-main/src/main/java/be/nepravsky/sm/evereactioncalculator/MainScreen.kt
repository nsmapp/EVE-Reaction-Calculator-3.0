package be.nepravsky.sm.evereactioncalculator

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxScope
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.material3.Button
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import be.nepravsky.sm.evereactioncalculator.model.Tabs
import be.nepravsky.sm.uikit.view.text.TextBold


@Composable
fun MainScreen(
    mainRouter: MainRouter,
    content: @Composable BoxScope.() -> Unit,
) {

    var tabState by remember { mutableStateOf<Tabs>(Tabs.REACTIONS) }


    Column(verticalArrangement = Arrangement.SpaceBetween) {
        TextBold(modifier = Modifier, text = "Main Screen")
        Box(modifier = Modifier
            .weight(1f)
            .fillMaxWidth()) { content() }
        Row(
            modifier = Modifier
                .wrapContentHeight()
                .fillMaxWidth()
        ) {
            Button(onClick = {
                tabState = Tabs.LIBRARY
                mainRouter.selectTab(Tabs.LIBRARY)

            }) { TextBold(text = "Library") }
            Button(onClick = {
                tabState = Tabs.REACTIONS
                mainRouter.selectTab(Tabs.REACTIONS)
            }) { TextBold(text = "Reactions") }
            Button(onClick = {
                tabState = Tabs.SETTINGS
                mainRouter.selectTab(Tabs.SETTINGS)
            }) { TextBold(text = "Settings") }
        }
    }

}
