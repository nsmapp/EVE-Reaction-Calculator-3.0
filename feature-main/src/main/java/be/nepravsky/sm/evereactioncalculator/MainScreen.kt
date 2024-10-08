package be.nepravsky.sm.evereactioncalculator

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxScope
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.navigationBarsPadding
//noinspection UsingMaterialAndMaterial3Libraries
import androidx.compose.material.BottomNavigation
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.filled.PlayArrow
import androidx.compose.material.icons.filled.Settings
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import be.nepravsky.sm.evereactioncalculator.model.Tabs
import be.nepravsky.sm.evereactioncalculator.view.NavigationItem
import be.nepravsky.sm.uikit.theme.AppTheme


@Composable
fun MainScreen(
    mainRouter: MainRouter,
    content: @Composable BoxScope.() -> Unit,
) {

    var tabState by remember { mutableStateOf<Tabs>(Tabs.REACTIONS) }


    Column(verticalArrangement = Arrangement.SpaceBetween) {

        Box(
            modifier = Modifier
                .navigationBarsPadding()
                .weight(1f)
                .fillMaxWidth()
        ) { content() }


        BottomNavigation(backgroundColor = AppTheme.colors.foreground_hard) {
            NavigationItem(
                isSelected = tabState == Tabs.LIBRARY,
                onClick = {
                    mainRouter.selectTab(Tabs.LIBRARY)
                    tabState = Tabs.LIBRARY
                },
                icon = Icons.Default.Menu
            )

            NavigationItem(
                isSelected = tabState == Tabs.REACTIONS,
                onClick = {
                    mainRouter.selectTab(Tabs.REACTIONS)
                    tabState = Tabs.REACTIONS
                },
                icon = Icons.Default.PlayArrow
            )

            NavigationItem(
                isSelected = tabState == Tabs.SETTINGS,
                onClick = {
                    mainRouter.selectTab(Tabs.SETTINGS)
                    tabState = Tabs.SETTINGS
                },
                icon = Icons.Default.Settings
            )
        }
    }

}