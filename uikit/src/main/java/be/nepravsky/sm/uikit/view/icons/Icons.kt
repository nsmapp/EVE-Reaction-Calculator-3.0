package be.nepravsky.sm.uikit.view.icons

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.res.painterResource
import be.nepravsky.sm.uikit.theme.AppTheme

@Composable
fun MicroIcon(
    modifier: Modifier = Modifier,
    painter: Painter
){
    BaseIcon(
        modifier = Modifier,
        painter = painterResource(android.R.drawable.btn_radio),
        size = AppTheme.viewSize.icon_micro,
        contentDescription = null,
    )
}

@Composable
fun SmallIcon(
    modifier: Modifier = Modifier,
    painter: Painter
){
    BaseIcon(
        modifier = Modifier,
        painter = painterResource(android.R.drawable.btn_radio),
        size = AppTheme.viewSize.icon_small,
        contentDescription = null,
    )
}

@Composable
fun NormalIcon(
    modifier: Modifier = Modifier,
    painter: Painter
){
    BaseIcon(
        modifier = Modifier,
        painter = painterResource(android.R.drawable.btn_radio),
        size = AppTheme.viewSize.icon_normal,
        contentDescription = null,
    )
}