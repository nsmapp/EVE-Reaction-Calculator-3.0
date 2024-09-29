package be.nepravsky.sm.uikit.view.textfield

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.defaultMinSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import be.nepravsky.sm.uikit.theme.AppTheme
import be.nepravsky.sm.uikit.theme.TEXT_EMPTY
import be.nepravsky.sm.uikit.view.text.TextBase


val decimalKeyboardOptions = KeyboardOptions.Default.copy(keyboardType = KeyboardType.Decimal)

@Composable
fun CBaseTextField(
    modifier: Modifier,
    value: String,
    onValueChange: (String) -> Unit,
    label: String = TEXT_EMPTY,
    enabled: Boolean = true,
    readOnly: Boolean = false,
    singleLine: Boolean = true,
    maxLines: Int = if (singleLine) 1 else Int.MAX_VALUE,
    minLines: Int = 1,
    textStyle: TextStyle = AppTheme.typography.medium,
    cursorBrush: Brush = SolidColor(AppTheme.colors.accent),
    keyboardOptions: KeyboardOptions = KeyboardOptions.Default,
    accentColor: Color = AppTheme.colors.accent
) {
    Column(
        modifier = modifier
            .wrapContentSize()
            .padding(AppTheme.padding.s_4)
    ) {

        TextBase(
            text = label,
            style = AppTheme.typography.regular_nano,
            color = AppTheme.colors.text
        )

        BasicTextField(
            modifier = Modifier
                .defaultMinSize(minHeight = 24.dp, minWidth = 48.dp)
                .drawBehind {
                    val strokeWidthPx = 1.dp.toPx()
                    val verticalOffset = size.height - 2.sp.toPx()
                    drawLine(
                        color = accentColor,
                        strokeWidth = strokeWidthPx,
                        start = Offset(0f, verticalOffset),
                        end = Offset(size.width, verticalOffset)
                    )
                },
            value = value,
            onValueChange = onValueChange,
            enabled = enabled,
            readOnly = readOnly,
            singleLine = singleLine,
            maxLines = maxLines,
            minLines = minLines,
            textStyle = textStyle.copy(
                color = AppTheme.colors.text,
            ),
            cursorBrush = cursorBrush,
            keyboardOptions = keyboardOptions,
        )
    }
}