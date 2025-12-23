package be.nepravsky.sm.evereactioncalculator.core

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import be.nepravsky.sm.evereactioncalculator.core.model.SettingsState
import be.nepravsky.sm.evereactioncalculator.core.model.SystemModel
import be.nepravsky.sm.evereactioncalculator.uikit.R
import be.nepravsky.sm.evereactioncalculator.core.view.SelectPriceLocationDialog
import be.nepravsky.sm.evereactioncalculator.core.view.SelectSearchLanguageDialog
import be.nepravsky.sm.evereactioncalculator.utils.TEXT_EMPTY
import be.nepravsky.sm.uikit.theme.AppTheme
import be.nepravsky.sm.uikit.view.row.KeyCheckRow
import be.nepravsky.sm.uikit.view.row.KeyValueRow
import org.koin.androidx.compose.koinViewModel

@Composable
fun SettingScreen(
    router: SettingsRouter,
) {

    val viewModel = koinViewModel<SettingsViewModel>()
    val state by viewModel.state.collectAsStateWithLifecycle()

    SettingsScreenView(
        state = state,
        onSetSearchLanguage =  remember(viewModel) { viewModel::setSearchLanguage },
        onHideDialogs = remember(viewModel) { viewModel::hideDialogs },
        onSetPriceLocation = remember(viewModel) { viewModel::setPriceLocation },
        onShowSearchLanguageDialog = remember(viewModel) { viewModel::showSearchLanguageDialog },
        onShowPriceLocationDialog = remember(viewModel) { viewModel::showPriceLocationDialog },
        onSetOfflineMode = remember(viewModel) { viewModel::setOfflineMode },
        onSetIsIgnoreFuelBlockBpc = remember(viewModel) { viewModel::setIsIgnoreFuelBlockBpc },
        onOpenAboutScreen = remember(viewModel) { router::openAboutScreen },
    )

}

@Composable
private fun SettingsScreenView(
    state: SettingsState,
    onSetSearchLanguage: (Long) -> Unit,
    onHideDialogs: () -> Unit,
    onSetPriceLocation: (SystemModel) -> Unit,
    onShowSearchLanguageDialog: () -> Unit,
    onShowPriceLocationDialog: () -> Unit,
    onSetOfflineMode: (Boolean) -> Unit,
    onSetIsIgnoreFuelBlockBpc: (Boolean) -> Unit,
    onOpenAboutScreen: () -> Unit,
) {
    if (state.isShowLanguageDialog) {
        SelectSearchLanguageDialog(
            onItemClick = onSetSearchLanguage,
            onDismissDialog = onHideDialogs,
            languages = state.languages
        )
    }

    if (state.isShowPriceLocationDialog) SelectPriceLocationDialog(
        onItemClick = onSetPriceLocation,
        onDismissDialog = onHideDialogs,
        systems = state.systems
    )

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(AppTheme.colors.foreground)
            .border(
                AppTheme.viewSize.border_small,
                AppTheme.colors.foreground_hard,
            ),
    ) {

        KeyValueRow(
            modifier = Modifier
                .clickable(onClick = onShowSearchLanguageDialog)
                .padding(vertical = AppTheme.padding.s_12)
                .padding(start = AppTheme.padding.s_8, end = AppTheme.padding.s_16)
                .fillMaxWidth()
                .wrapContentHeight(),
            key = stringResource(R.string.feature_settings_search_language),
            value = state.langName,
            style = AppTheme.typography.medium,
        )

        KeyValueRow(
            modifier = Modifier
                .clickable(onClick = onShowPriceLocationDialog)
                .padding(vertical = AppTheme.padding.s_12)
                .padding(start = AppTheme.padding.s_8, end = AppTheme.padding.s_16)
                .fillMaxWidth()
                .wrapContentHeight(),
            key = stringResource(R.string.feature_settings_price_location),
            value = state.systemName,
            style = AppTheme.typography.medium,
        )

        KeyCheckRow(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = AppTheme.padding.s_8),
            key = stringResource(R.string.feature_settings_offline_mode),
            checked = state.isOfflineMode,
            onCheckedChange = onSetOfflineMode,
            style = AppTheme.typography.medium
        )

        KeyCheckRow(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = AppTheme.padding.s_8),
            key = stringResource(R.string.feature_settings_ignore_fuel_bpc),
            checked = state.isIgnoreFuelBlock,
            onCheckedChange = onSetIsIgnoreFuelBlockBpc,
            style = AppTheme.typography.medium
        )

        KeyValueRow(
            modifier = Modifier
                .padding(vertical = AppTheme.padding.s_12)
                .padding(start = AppTheme.padding.s_8, end = AppTheme.padding.s_16)
                .fillMaxWidth()
                .clickable(onClick = onOpenAboutScreen)
                .wrapContentHeight(),
            key = "About",
            value = TEXT_EMPTY,
            style = AppTheme.typography.medium,
        )
    }
}