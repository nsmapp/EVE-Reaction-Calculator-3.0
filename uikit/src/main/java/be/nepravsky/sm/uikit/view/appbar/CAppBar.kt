package be.nepravsky.sm.uikit.view.appbar

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import be.nepravsky.sm.uikit.theme.AppTheme
import be.nepravsky.sm.uikit.view.text.TextBoldLarge

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CAppBar(
    modifier: Modifier = Modifier,
    text: String,
    onBackPressed: () -> Unit = {},


    ) {
    TopAppBar(
        modifier = modifier,
        colors = TopAppBarDefaults.topAppBarColors(
            containerColor = AppTheme.colors.foreground,
            titleContentColor = AppTheme.colors.transparent,
        ),
        title = {
            TextBoldLarge(text = text)
        },
        navigationIcon = {
            IconButton(onClick = onBackPressed) {
                Icon(
                    //TODO replace icon?
                    imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                    tint = AppTheme.colors.text,
                    contentDescription = null
                )
            }
        },
    )

}