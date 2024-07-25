package be.nepravsky.sm.uikit.theme.dimens

import androidx.compose.runtime.Immutable
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp


@Immutable
data class ViewSize(
    val icon_12: Dp,
    val icon_24: Dp,
    val icon_48: Dp,

    val border_zero: Dp,
    val border_small: Dp,
    val border_normal: Dp

)

val defaultViewSizes = ViewSize(
    icon_12 = 12.dp,
    icon_24 = 24.dp,
    icon_48 = 48.dp,

    border_zero = 0.dp,
    border_small = 1.dp,
    border_normal = 2.dp,

)