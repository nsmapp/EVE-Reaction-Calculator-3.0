package be.nepravsky.sm.evereactioncalculator.settings

import androidx.compose.runtime.Composable
import be.nepravsky.sm.evereactioncalculator.navigation.Rout
import be.nepravsky.sm.evereactioncalculator.core.SettingScreen
import be.nepravsky.sm.evereactioncalculator.core.SettingsRouter
import be.nepravsky.sm.evereactioncalculator.core.SettingsViewModel
import be.nepravsky.sm.evereactioncalculator.viewmodel.viewModelKey
import com.arkivanov.decompose.ComponentContext

class SettingsRouterImpl(
    componentContext: ComponentContext,
    private val onOpenAboutScreen: () -> Unit,
): Rout(
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

    override fun openAboutScreen() {
        onOpenAboutScreen.invoke()
    }
}