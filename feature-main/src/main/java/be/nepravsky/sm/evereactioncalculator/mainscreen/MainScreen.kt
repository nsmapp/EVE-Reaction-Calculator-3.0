package be.nepravsky.sm.evereactioncalculator.mainscreen

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxScope
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.statusBarsPadding
//noinspection UsingMaterialAndMaterial3Libraries
import androidx.compose.material.BottomNavigation
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.filled.PlayArrow
import androidx.compose.material.icons.filled.Settings
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import be.nepravsky.sm.evereactioncalculator.mainscreen.model.Tabs
import be.nepravsky.sm.evereactioncalculator.mainscreen.view.NavigationItem
import be.nepravsky.sm.uikit.theme.AppTheme


@Composable
fun MainScreen(
    viewModel: MainViewModel,
    router: MainRouter,
    content: @Composable BoxScope.() -> Unit,
) {

    val tabState by viewModel.activeTab.collectAsStateWithLifecycle()

    LaunchedEffect(null) {
        viewModel.channel.collect {
            router.selectTab(it)
        }
    }

    Column(
        modifier = Modifier.navigationBarsPadding().statusBarsPadding(),
        verticalArrangement = Arrangement.SpaceBetween
    ) {
        Box(
            modifier = Modifier
                .weight(1f)
                .fillMaxWidth()
        ) { content() }


        BottomNavigation(backgroundColor = AppTheme.colors.foreground_hard) {
            NavigationItem(
                isSelected = tabState == Tabs.LIBRARY,
                onClick = { viewModel.setActiveTab(Tabs.LIBRARY) },
                icon = Icons.Default.Menu
            )

            NavigationItem(
                isSelected = tabState == Tabs.REACTIONS,
                onClick = { viewModel.setActiveTab(Tabs.REACTIONS) },
                icon = Icons.Default.PlayArrow
            )

            NavigationItem(
                isSelected = tabState == Tabs.SETTINGS,
                onClick = { viewModel.setActiveTab(Tabs.SETTINGS) },
                icon = Icons.Default.Settings
            )
        }
    }

}