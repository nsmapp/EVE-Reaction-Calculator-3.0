package be.belveb.builder.view

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.BoxScope
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.ColorFilter
import be.nepravsky.sm.uikit.theme.AppTheme
import be.nepravsky.sm.uikit.view.icons.SmallIcon

@Composable
fun BoxScope.SwipeMenuView(
    onDeleteClick: () -> Unit,
) {
    Row(
        modifier = Modifier
            .padding(horizontal = AppTheme.padding.s_8)
            .align(Alignment.CenterEnd)
    ) {

        SmallIcon(
            modifier = Modifier
                .clickable { onDeleteClick() }
                .padding(AppTheme.padding.s_8),
            imageVector = Icons.Default.Delete,
            colorFilter = ColorFilter.tint(AppTheme.colors.text),
        )

    }
}