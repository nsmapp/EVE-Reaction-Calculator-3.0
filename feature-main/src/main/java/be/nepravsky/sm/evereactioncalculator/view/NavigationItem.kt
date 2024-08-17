package be.nepravsky.sm.evereactioncalculator.view

import androidx.compose.foundation.layout.RowScope
import androidx.compose.material.BottomNavigationItem
import androidx.compose.runtime.Composable
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.graphics.vector.ImageVector
import be.nepravsky.sm.uikit.theme.AppTheme
import be.nepravsky.sm.uikit.view.icons.SmallIcon

@Composable
fun RowScope.NavigationItem(
    isSelected: Boolean,
    onClick: () -> Unit,
    icon: ImageVector
) {

    val selectedColor = AppTheme.colors.accent
    val unselectedColor = AppTheme.colors.text
    val tint by remember {
        derivedStateOf {
            if (isSelected) selectedColor else unselectedColor
        }
    }

    BottomNavigationItem(
        selected = isSelected,
        onClick = onClick,
        icon = {
            SmallIcon(
                imageVector = icon,
                colorFilter = ColorFilter.tint(tint),
            )
        })
}