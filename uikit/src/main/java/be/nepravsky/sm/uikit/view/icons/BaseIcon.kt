package be.nepravsky.sm.uikit.view.icons

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import be.nepravsky.sm.uikit.theme.AppTheme

@Composable
internal fun BaseIcon(
    modifier: Modifier,
    painter: Painter,
    size: Dp,
    contentDescription: String?,
    colorFilter: ColorFilter? = null,

    ){
    Image(
        modifier = modifier.size(size),
        painter = painter,
        contentDescription = contentDescription,
        colorFilter = colorFilter
    )
}


@Preview
@Composable
fun PreviewBaseIcon(){
    BaseIcon(
        modifier = Modifier,
        painter = painterResource(android.R.drawable.btn_radio),
        size = AppTheme.viewSize.icon_small,
        contentDescription = null,
    )
}