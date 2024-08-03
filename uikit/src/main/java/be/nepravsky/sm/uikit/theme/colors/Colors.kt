package be.nepravsky.sm.uikit.theme.colors

import androidx.compose.material3.darkColorScheme
import androidx.compose.runtime.Immutable
import androidx.compose.runtime.Stable
import androidx.compose.ui.graphics.Color

private val defBackground: Color = Color(0xff44475a)
private val defForeground: Color = Color(0xff282a36)
private val defForegroundLight: Color = Color(0xFF373846)
private val defText: Color = Color(0xfff8f8f2)
private val defTextLigth: Color = Color(0xC69B9B9A)
private val defAccent: Color = Color(0xFF205F2F)
private val defAccentLight: Color = Color(0x89205F2F)
private val defTransparent: Color = Color(0x00FFFFFF)

@Immutable
data class Colors(
    val background: Color = defBackground,
    val foreground: Color = defForeground,
    val foreground_light: Color = defForegroundLight,
    val text: Color = defText,
    val text_ligth: Color = defTextLigth,
    val accent: Color = defAccent,
    val accent_light: Color = defAccentLight,
    val transparent: Color = defTransparent,
)

@Stable
val dayColorScheme = darkColorScheme(
    primary = defBackground,
    primaryContainer = defForeground,
    onSurface = defBackground
)

