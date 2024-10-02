package be.nepravsky.sm.evereactioncalculator

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import be.nepravsky.sm.evereactioncalculator.uikit.R
import be.nepravsky.sm.uikit.theme.AppTheme
import be.nepravsky.sm.uikit.view.row.KeyCheckRow

@Composable
fun SettingScreen(
    viewModel: SettingsViewModel,
    router: SettingsRouter,
) {

    val state by viewModel.state.collectAsState()

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(AppTheme.colors.foreground)
            .border(
                AppTheme.viewSize.border_small,
                AppTheme.colors.foreground_hard,
            ),
    ) {

        KeyCheckRow(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = AppTheme.padding.s_8),
            key = stringResource(R.string.feature_settings_offline_mode),
            checked = state.isOfflineMode,
            onCheckedChange = {isChecked ->
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

    }
}