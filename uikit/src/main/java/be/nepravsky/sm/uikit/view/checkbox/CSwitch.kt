package be.nepravsky.sm.uikit.view.checkbox




//noinspection UsingMaterialAndMaterial3Libraries
import androidx.compose.material.Switch
//noinspection UsingMaterialAndMaterial3Libraries
import androidx.compose.material.SwitchDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import be.nepravsky.sm.uikit.theme.AppTheme

@Composable
fun CSwitch(
    modifier: Modifier = Modifier,
    checked: Boolean,
    onCheckedChange: ((Boolean) -> Unit)?,
) {

    Switch(
        modifier = modifier,
        checked = checked,
        onCheckedChange = onCheckedChange,
        colors = SwitchDefaults.colors(
            checkedThumbColor = AppTheme.colors.accent,
            checkedTrackColor = AppTheme.colors.accent_light,
            uncheckedThumbColor = AppTheme.colors.background,
            uncheckedTrackColor = AppTheme.colors.transparent,

        )
    )
}