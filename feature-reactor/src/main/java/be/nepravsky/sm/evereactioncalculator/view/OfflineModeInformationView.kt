package be.nepravsky.sm.evereactioncalculator.view

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import be.nepravsky.sm.evereactioncalculator.uikit.R
import be.nepravsky.sm.uikit.theme.AppTheme
import be.nepravsky.sm.uikit.view.text.TextBold
import be.nepravsky.sm.uikit.view.text.TextMediumSmall


@Composable
fun OfflineModeInformationView(onDisableOfflineMode: () -> Unit) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .background(AppTheme.colors.foreground_hard),
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        TextMediumSmall(
            modifier = Modifier.padding(start = AppTheme.padding.s_8),
            text = stringResource(R.string.feature_reactor_offline_mode_enabled)
        )
        TextBold(
            modifier = Modifier
                .padding(end = AppTheme.padding.s_8)
                .clickable { onDisableOfflineMode() },
            text = stringResource(R.string.feature_reactor_disable)
        )
    }
}