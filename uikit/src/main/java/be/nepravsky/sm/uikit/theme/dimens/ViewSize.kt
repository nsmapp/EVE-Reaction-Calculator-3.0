package be.nepravsky.sm.uikit.theme.dimens

import androidx.compose.runtime.Immutable
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp


@Immutable
data class ViewSize(
    val icon_micro: Dp,
    val icon_small: Dp,
    val icon_normal: Dp,

    val border_zero: Dp,
    val border_small: Dp,
    val border_normal: Dp

)

val defaultViewSizes = ViewSize(
    icon_micro = 12.dp,
    icon_small = 24.dp,
    icon_normal = 48.dp,

    border_zero = 0.dp,
    border_small = 1.dp,
    border_normal = 2.dp,

)