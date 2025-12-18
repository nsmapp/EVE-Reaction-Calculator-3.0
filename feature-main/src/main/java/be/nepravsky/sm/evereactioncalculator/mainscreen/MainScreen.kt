package be.nepravsky.sm.evereactioncalculator.mainscreen

//noinspection UsingMaterialAndMaterial3Libraries
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxScope
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.material.BottomNavigation
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.filled.PlayArrow
import androidx.compose.material.icons.filled.Sd
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material3.NavigationBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import be.nepravsky.sm.evereactioncalculator.mainscreen.model.Tabs
import be.nepravsky.sm.evereactioncalculator.mainscreen.view.NavigationBarItem
import be.nepravsky.sm.uikit.theme.AppTheme
import org.koin.androidx.compose.koinViewModel


@Composable
fun MainScreen(
    viewModel: MainViewModel = koinViewModel(),
    router: MainRouter,
    content: @Composable () -> Unit,
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


        NavigationBar {
            NavigationBarItem(
                isSelected = tabState == Tabs.LIBRARY,
                onClick = {viewModel.setActiveTab(Tabs.LIBRARY) },
                icon = Icons.Default.Menu
            )

            NavigationBarItem(
                isSelected = tabState == Tabs.REACTIONS,
                onClick = {viewModel.setActiveTab(Tabs.REACTIONS) },
                icon = Icons.Default.PlayArrow
            )

            NavigationBarItem(
                isSelected = tabState == Tabs.SETTINGS,
                onClick = {viewModel.setActiveTab(Tabs.SETTINGS) },
                icon = Icons.Default.Settings
            )
        }
    }

}