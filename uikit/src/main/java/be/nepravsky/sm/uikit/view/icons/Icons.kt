package be.nepravsky.sm.uikit.view.icons

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.graphics.vector.ImageVector
import be.nepravsky.sm.uikit.theme.AppTheme

@Composable
fun MicroIcon(
    modifier: Modifier = Modifier,
    imageVector: ImageVector,
    colorFilter: ColorFilter? = null,
){
    BaseIcon(
        modifier = modifier,
        imageVector = imageVector,
        size = AppTheme.viewSize.icon_micro,
        contentDescription = null,
        colorFilter = colorFilter,
    )
}

@Composable
fun SmallIcon(
    modifier: Modifier = Modifier,
    imageVector: ImageVector,
    colorFilter: ColorFilter? = null,
){
    BaseIcon(
        modifier = modifier,
        imageVector = imageVector,
        size = AppTheme.viewSize.icon_small,
        contentDescription = null,
        colorFilter = colorFilter,
    )
}

@Composable
fun NormalIcon(
    modifier: Modifier = Modifier,
    imageVector: ImageVector,
    colorFilter: ColorFilter? = null,
){
    BaseIcon(
        modifier = modifier,
        imageVector = imageVector,
        size = AppTheme.viewSize.icon_normal,
        contentDescription = null,
        colorFilter = colorFilter,
    )
}