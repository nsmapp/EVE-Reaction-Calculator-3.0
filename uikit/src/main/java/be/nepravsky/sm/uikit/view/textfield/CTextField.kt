package be.nepravsky.sm.uikit.view.textfield

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.border
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.text.selection.TextSelectionColors
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldColors
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.tooling.preview.Preview
import be.nepravsky.sm.uikit.theme.AppTheme
import be.nepravsky.sm.uikit.view.icons.MicroIcon
import be.nepravsky.sm.uikit.view.text.TextMedium

@Composable
fun CTextField(
    modifier: Modifier = Modifier,
    value: String,
    onValueChange: (String) -> Unit,
    enabled: Boolean = true,
    readOnly: Boolean = false,
    textStyle: TextStyle = AppTheme.typography.medium,
    hint: String? = null,
    leadingIcon: Painter? = null,
    trailingIcon: Painter? = null,
    isError: Boolean = false,
    keyboardOptions: KeyboardOptions = KeyboardOptions.Default,
    keyboardActions: KeyboardActions = KeyboardActions.Default,
    singleLine: Boolean = true,
    maxLines: Int = if (singleLine) 1 else Int.MAX_VALUE,
    shape: Shape = RoundedCornerShape(AppTheme.radius.r_8),
    colors: TextFieldColors = TextFieldDefaults.colors()
        .copy(
            focusedTextColor = AppTheme.colors.text,
            unfocusedTextColor = AppTheme.colors.text,
            disabledTextColor = AppTheme.colors.text_ligth,
            focusedContainerColor = AppTheme.colors.foreground_light,
            unfocusedContainerColor = AppTheme.colors.foreground,
            disabledContainerColor = AppTheme.colors.foreground,
            cursorColor = AppTheme.colors.accent,
            textSelectionColors = TextSelectionColors(
                handleColor = AppTheme.colors.accent,
                backgroundColor = AppTheme.colors.foreground),
            focusedPlaceholderColor = AppTheme.colors.text_ligth,
            focusedIndicatorColor = AppTheme.colors.transparent,
            unfocusedIndicatorColor = AppTheme.colors.transparent

        )
) {

    TextField(
        modifier = modifier.border(
            border = BorderStroke(
                width = AppTheme.viewSize.border_normal,
                color = AppTheme.colors.accent
            ),
            shape = shape
        ),
        value = value,
        onValueChange = onValueChange,
        enabled = enabled,
        readOnly = readOnly,
        textStyle = textStyle,
        placeholder = { hint?.let { TextMedium(text = hint, color = colors.focusedPlaceholderColor) } },
        leadingIcon = { leadingIcon?.let { painter -> MicroIcon(painter = painter) } },
        trailingIcon = { trailingIcon?.let { painter -> MicroIcon(painter = painter) } },
        isError = isError,
        keyboardOptions = keyboardOptions,
        keyboardActions = keyboardActions,
        singleLine = singleLine,
        maxLines = maxLines,
        shape = shape,
        colors = colors,
    )

}


@Preview
@Composable
fun PreviewTextField() {
    CTextField(
        value = "",
        onValueChange = {},
        hint = "hint text",
        leadingIcon = painterResource(android.R.drawable.ic_menu_search),
        trailingIcon = painterResource(android.R.drawable.ic_menu_search),

        )
}
