package be.nepravsky.sm.evereactioncalculator.mainscreen.view

import androidx.compose.foundation.layout.RowScope
//noinspection UsingMaterialAndMaterial3Libraries
import androidx.compose.material.BottomNavigationItem
import androidx.compose.material3.NavigationBarItem
import androidx.compose.runtime.Composable
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.graphics.vector.ImageVector
import be.nepravsky.sm.uikit.theme.AppTheme
import be.nepravsky.sm.uikit.view.icons.SmallIcon

@Composable
fun RowScope.NavigationBarItem(
    isSelected: Boolean,
    onClick: () -> Unit,
    icon: ImageVector
) {

    val selectedColor = AppTheme.colors.accent
    val unselectedColor = AppTheme.colors.text
    val tint by remember(isSelected) {
        derivedStateOf {
            if (isSelected) selectedColor else unselectedColor
        }
    }
    NavigationBarItem(
        selected = isSelected,
        onClick = onClick,
        icon = {
            SmallIcon(
                imageVector = icon,
                colorFilter = ColorFilter.tint(tint),
            )
        },
    )
}