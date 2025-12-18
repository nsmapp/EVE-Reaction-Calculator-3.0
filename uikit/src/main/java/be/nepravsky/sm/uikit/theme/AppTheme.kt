package be.nepravsky.sm.uikit.theme

import android.app.Activity
import androidx.compose.foundation.layout.BoxScope
import androidx.compose.material3.ColorScheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.SnackbarHostState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.Stable
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.staticCompositionLocalOf
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.platform.LocalView
import androidx.core.view.WindowCompat
import be.nepravsky.sm.uikit.theme.colors.Colors
import be.nepravsky.sm.uikit.theme.colors.dayColorScheme
import be.nepravsky.sm.uikit.theme.dimens.Padding
import be.nepravsky.sm.uikit.theme.dimens.Radius
import be.nepravsky.sm.uikit.theme.dimens.ViewSize
import be.nepravsky.sm.uikit.theme.dimens.defaultPaddings
import be.nepravsky.sm.uikit.theme.dimens.defaultRadius
import be.nepravsky.sm.uikit.theme.dimens.defaultViewSizes
import be.nepravsky.sm.uikit.theme.typography.UbuntuTypography
import be.nepravsky.sm.uikit.theme.typography.ubuntuTypography
import kotlinx.coroutines.CoroutineScope


@Composable
fun AppTheme(
    isDarkThemeEnabled: Boolean = true,
    accentColor: Color = Colors().accent,
    snackBarHost: @Composable BoxScope.() -> Unit = {},
    content: @Composable () -> Unit
){
    val typography = ubuntuTypography
    val padding = defaultPaddings
    val radius = defaultRadius
    val viewSize = defaultViewSizes
    //TODO add day/night colors
    val colors = if (isDarkThemeEnabled) Colors() else Colors()
    val colorScheme = if (isDarkThemeEnabled) dayColorScheme else dayColorScheme

    val snackBarHostState = remember { SnackbarHostState() }
    val coroutineScope = rememberCoroutineScope()


    SystemAppearance(isDarkThemeEnabled = isDarkThemeEnabled)

    CompositionLocalProvider(
        LocalTypography provides typography,
        LocalPadding provides  padding,
        LocalRadius provides radius,
        LocalViewSize provides  viewSize,
        LocalColors provides colors,
        LocalAccentColor provides accentColor,
        LocalColorScheme provides  colorScheme,
        LocalSnackBarHostState provides snackBarHostState,
        LocalCoroutineScope provides coroutineScope
    ) {
        MaterialTheme(
            content = content,
            colorScheme = colorScheme
        )
    }


}

internal const val TEXT_EMPTY = ""
val LocalTypography = staticCompositionLocalOf<UbuntuTypography> { error(TEXT_EMPTY)  }
val LocalPadding = staticCompositionLocalOf<Padding> { error(TEXT_EMPTY) }
val LocalRadius = staticCompositionLocalOf<Radius> { error(TEXT_EMPTY) }
val LocalViewSize = staticCompositionLocalOf<ViewSize> { error(TEXT_EMPTY) }
val LocalColors = staticCompositionLocalOf<Colors> { error(TEXT_EMPTY) }
val LocalAccentColor = staticCompositionLocalOf<Color> { error(TEXT_EMPTY) }
val LocalColorScheme = staticCompositionLocalOf<ColorScheme> { error(TEXT_EMPTY) }
val LocalSnackBarHostState = staticCompositionLocalOf<SnackbarHostState> { error(TEXT_EMPTY) }
val LocalCoroutineScope = staticCompositionLocalOf<CoroutineScope> { error(TEXT_EMPTY) }


@Stable
object AppTheme{
    val typography: UbuntuTypography
        @Composable get() = LocalTypography.current

    val padding: Padding
        @Composable get() = LocalPadding.current

    val radius: Radius
        @Composable get() = LocalRadius.current

    val viewSize: ViewSize
        @Composable get() = LocalViewSize.current

    val colors: Colors
        @Composable get() = LocalColors.current

    val accentColor: Color
        @Composable get() = LocalAccentColor.current

}

@Composable
private fun SystemAppearance(isDarkThemeEnabled: Boolean) {
    val view = LocalView.current
    val statusBarColor = Colors().foreground.toArgb()
    LaunchedEffect(isDarkThemeEnabled) {
        val window = (view.context as Activity).window
        window.statusBarColor = statusBarColor
        WindowCompat.getInsetsController(window, window.decorView).apply {
            isAppearanceLightStatusBars = isDarkThemeEnabled.not()
            isAppearanceLightNavigationBars = isDarkThemeEnabled.not()
        }
    }
}