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
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import be.nepravsky.sm.evereactioncalculator.uikit.R
import be.nepravsky.sm.evereactioncalculator.core.view.SelectPriceLocationDialog
import be.nepravsky.sm.evereactioncalculator.core.view.SelectSearchLanguageDialog
import be.nepravsky.sm.evereactioncalculator.utils.TEXT_EMPTY
import be.nepravsky.sm.uikit.theme.AppTheme
import be.nepravsky.sm.uikit.view.row.KeyCheckRow
import be.nepravsky.sm.uikit.view.row.KeyValueRow

@Composable
fun SettingScreen(
    viewModel: SettingsViewModel,
    router: SettingsRouter,
) {

    val state by viewModel.state.collectAsState()

    if (state.isShowLanguageDialog) SelectSearchLanguageDialog(
        onItemClick = { languageModel -> viewModel.setSearchLanguage(languageModel.id) },
        onDismissDialog = { viewModel.hideDialogs() },
        languages = state.languages
    )

    if (state.isShowPriceLocationDialog) SelectPriceLocationDialog(
        onItemClick = { systemModel ->  viewModel.setPriceLocation(systemModel)},
        onDismissDialog = {viewModel.hideDialogs()},
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
                .clickable { viewModel.showSearchLanguageDialog() }
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
                .clickable { viewModel.showPriceLocationDialog() }
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
            onCheckedChange = { isChecked ->
                viewModel.setOfflineMode(isChecked)
            },
            style = AppTheme.typography.medium
        )

        KeyCheckRow(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = AppTheme.padding.s_8),
            key = stringResource(R.string.feature_settings_ignore_fuel_bpc),
            checked = state.isIgnoreFuelBlock,
            onCheckedChange = { isChecked ->
                viewModel.setIsIgnoreFuelBlockBpc(isChecked)
            },
            style = AppTheme.typography.medium
        )

        KeyValueRow(
            modifier = Modifier
                .clickable { viewModel.showPriceLocationDialog() }
                .padding(vertical = AppTheme.padding.s_12)
                .padding(start = AppTheme.padding.s_8, end = AppTheme.padding.s_16)
                .fillMaxWidth()
                .clickable { router.openAboutScreen() }
                .wrapContentHeight(),
            key = "About",
            value = TEXT_EMPTY,
            style = AppTheme.typography.medium,
        )

    }
}