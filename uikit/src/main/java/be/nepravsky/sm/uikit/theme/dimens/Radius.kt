package be.nepravsky.sm.uikit.theme.dimens

import androidx.compose.runtime.Immutable
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp

@Immutable
data class Radius(
    val zero: Dp,
    val r_2: Dp,
    val r_4: Dp,
    val r_8: Dp,
    val r_16: Dp,
)

val defaultRadius = Radius(
    zero = 0.dp,
    r_2 = 2.dp,
    r_4 = 4.dp,
    r_8 = 8.dp,
    r_16 = 16.dp,
)