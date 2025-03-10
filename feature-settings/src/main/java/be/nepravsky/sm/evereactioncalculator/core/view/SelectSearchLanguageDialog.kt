package be.nepravsky.sm.evereactioncalculator.core.view

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.window.Dialog
import be.nepravsky.sm.evereactioncalculator.core.model.LanguageModel
import be.nepravsky.sm.evereactioncalculator.uikit.R
import be.nepravsky.sm.uikit.theme.AppTheme
import be.nepravsky.sm.uikit.view.button.CButton
import be.nepravsky.sm.uikit.view.text.TextMedium

@Composable
fun SelectSearchLanguageDialog(
    onItemClick: (LanguageModel) -> Unit,
    onDismissDialog: () -> Unit,
    languages: List<LanguageModel>,
) {
    Dialog(onDismissRequest = onDismissDialog) {
        Column(
            modifier = Modifier
                .background(
                    color = AppTheme.colors.foreground,
                    shape = RoundedCornerShape(AppTheme.radius.r_16)
                )
                .border(
                    border = BorderStroke(
                        AppTheme.viewSize.border_normal,
                        AppTheme.colors.foreground_hard
                    ),
                    shape = RoundedCornerShape(AppTheme.radius.r_16)
                )
                .padding(AppTheme.padding.s_8),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {

            TextMedium(
                modifier = Modifier.padding(AppTheme.padding.s_16),
                text = stringResource(R.string.feature_settings_select_search_language),
                style = AppTheme.typography.medium_large
            )

            Column(
                modifier = Modifier
                    .padding(horizontal = AppTheme.padding.s_8)
                    .wrapContentSize()
            ) {
                languages.forEach { item ->
                    CButton(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(vertical = AppTheme.padding.s_2),
                        onClick = { onItemClick(item) },
                        text = item.name
                    )
                }
            }
        }
    }
}