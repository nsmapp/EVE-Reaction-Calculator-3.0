package be.nepravsky.sm.evereactioncalculator.reaction.searchettings

import androidx.compose.runtime.Composable
import be.nepravsky.sm.evereactioncalculator.navigation.Rout
import be.belveb.searchsettings.SearchSettingsScreen
import be.belveb.searchsettings.SearchSettingsViewModel
import be.belveb.searchsettings.contract.SearchSettingsRouter
import be.nepravsky.sm.evereactioncalculator.viewmodel.viewModelKey
import com.arkivanov.decompose.ComponentContext

class SearchSettingsRouterImpl(
    componentContext: ComponentContext,
    private val onBackPressed:() -> Unit,
): Rout(
    componentContext = componentContext,
    viewModelKey = SearchSettingsViewModel::class.viewModelKey()
), SearchSettingsRouter {

    @Composable
    override fun Content() {
        SearchSettingsScreen(decomposeViewModel(), this)
    }

    override fun onFinish() {
        onBackPressed()
    }
}