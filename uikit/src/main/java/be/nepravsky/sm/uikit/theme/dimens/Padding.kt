package be.nepravsky.sm.uikit.theme.dimens

import androidx.compose.runtime.Immutable
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp


@Immutable
data class Padding(
    val zero: Dp,
    val s_2: Dp,
    val s_4: Dp,
    val s_8: Dp,
    val s_12: Dp,
    val s_16: Dp,
    val s_20: Dp,
    val s_24: Dp,
    val s_32: Dp,
    val s_64: Dp,
)

val defaultPaddings = Padding(
    zero = 0.dp,
    s_2 = 2.dp,
    s_4 = 4.dp,
    s_8 = 8.dp,
    s_12 = 12.dp,
    s_16 = 16.dp,
    s_20 = 20.dp,
    s_24 = 24.dp,
    s_32 = 32.dp,
    s_64 = 64.dp,
)