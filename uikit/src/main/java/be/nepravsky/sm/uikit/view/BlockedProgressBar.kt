package be.nepravsky.sm.uikit.view

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import be.nepravsky.sm.uikit.theme.AppTheme

@Composable
fun FullScreenProgressBox(
    size: Dp = 64.dp,
    isShowProgress: Boolean,
    content: @Composable () -> Unit,
) {

    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.TopCenter
    ) {

        content()

        if (isShowProgress)
            Box(
                modifier = Modifier
                    .clickable(false, onClick = {})
                    .fillMaxSize()
                    .background(AppTheme.colors.foregraund_light_a50),
                contentAlignment = Alignment.Center
            ) {
                CircularProgressIndicator(
                    modifier = Modifier.size(size),
                    trackColor = AppTheme.colors.accent,
                )
            }
    }
}