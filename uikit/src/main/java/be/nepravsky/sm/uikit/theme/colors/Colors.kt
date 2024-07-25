package be.nepravsky.sm.uikit.theme.colors

import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Immutable
import androidx.compose.runtime.Stable
import androidx.compose.ui.graphics.Color


@Immutable
data class Colors(
    val background_item: Color,
    val background_item_light: Color,
    val background: Color,
    val background_light: Color,
    val border_hard: Color,
    val border_light: Color,
    val text_hard: Color,
    val text_light: Color,

    val default_accent: Color,
    val default_accent_second: Color,
)

val nightColors = Colors(
    background_item = Color(0xFF524B4B),
    background_item_light = Color(0xFF665D5D),
    background = Color(0xFFC0C0C0),
    background_light = Color(0xFFC9C9C9),
    text_hard = Color(0xFFDFDFDF),
    text_light = Color(0xB3DFDFDF),
    border_hard = Color(0xFF005b9f),
    border_light = Color(0xFF017CD8),
    default_accent = Color(0xFF3A373C),
    default_accent_second = Color(0xFF3C5A5A),

)


val dayColors = Colors(
    background_item = Color(0xFF865A5A),
    background_item_light = Color(0xFFA33E3E),
    background = Color(0xFF4f5b62),
    background_light = Color(0xFFDADADA),
    text_hard = Color(0xFF000000),
    text_light = Color(0x80000000),
    border_hard = Color(0xFF005b9f),
    border_light = Color(0xFF017CD8),
    default_accent = Color(0xFF7B4DD1),
    default_accent_second = Color(0xFF665D5D),
)

@Stable
val dayColorScheme = lightColorScheme()

@Stable
val nightColorScheme = darkColorScheme()