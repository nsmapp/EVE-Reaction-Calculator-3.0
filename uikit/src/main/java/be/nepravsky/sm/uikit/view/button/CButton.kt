package be.nepravsky.sm.uikit.view.button

import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import be.nepravsky.sm.uikit.theme.AppTheme
import be.nepravsky.sm.uikit.view.text.TextBase

@Composable
fun CButton(
    modifier: Modifier = Modifier,
    onClick: () -> Unit,
    text: String,

){

    Button(
        modifier = modifier,
        onClick = onClick,
        shape = RoundedCornerShape(AppTheme.padding.s_8)
    ) {
        TextBase(
            modifier = Modifier.padding(
                horizontal = AppTheme.padding.s_8,
                vertical = AppTheme.padding.s_4,
            ),
            text = text,
            style = AppTheme.typography.medium
        )
    }
}