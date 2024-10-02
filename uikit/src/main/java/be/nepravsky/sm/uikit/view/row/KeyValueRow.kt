package be.nepravsky.sm.uikit.view.row

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextStyle
import be.nepravsky.sm.uikit.theme.AppTheme
import be.nepravsky.sm.uikit.view.checkbox.CSwitch
import be.nepravsky.sm.uikit.view.text.TextBase

@Composable
fun KeyValueRow(
    modifier: Modifier = Modifier,
    key: String,
    value: String,
    style: TextStyle = AppTheme.typography.medium_small,
) {
    Row(
        modifier = modifier,
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        TextBase(text = key, style = style, maxLines = 1)
        TextBase(text = value, style = style, maxLines = 1)
    }
}


@Composable
fun KeyValueRow(
    modifier: Modifier = Modifier,
    key: String,
    value: Long,
    style: TextStyle = AppTheme.typography.medium_small,
) {
    KeyValueRow(
        modifier = modifier,
        key = key,
        value = value.toString(),
        style = style,
    )
}

@Composable
fun KeyCheckRow(
    modifier: Modifier = Modifier,
    key: String,
    checked: Boolean,
    onCheckedChange: ((Boolean) -> Unit)?,
    style: TextStyle = AppTheme.typography.medium,
) {
    Row(
        modifier = modifier,
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        TextBase(text = key, style = style, maxLines = 1)
        CSwitch(
            checked = checked,
            onCheckedChange = onCheckedChange
        )
    }
}