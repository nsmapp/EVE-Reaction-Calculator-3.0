package be.nepravsky.sm.evereactioncalculator.settings

import androidx.compose.runtime.Composable
import be.nepravsky.sm.evereactioncalculator.navigation.Rout
import be.nepravsky.sm.evereactioncalculator.SettingScreen
import be.nepravsky.sm.evereactioncalculator.SettingsRouter
import be.nepravsky.sm.evereactioncalculator.SettingsViewModel
import be.nepravsky.sm.evereactioncalculator.viewmodel.viewModelKey
import com.arkivanov.decompose.ComponentContext

class SettingsRouterImpl(componentContext: ComponentContext): Rout(
    componentContext = componentContext,
    viewModelKey = SettingsViewModel::class.viewModelKey(),
), SettingsRouter {


    @Composable
    override fun Content() {
        SettingScreen(
            viewModel = decomposeViewModel(),
            router = this,
        )
    }
}