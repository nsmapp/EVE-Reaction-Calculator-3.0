package be.nepravsky.builder.view

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.vector.rememberVectorPainter
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import be.nepravsky.sm.evereactioncalculator.utils.getItemImageURL
import be.nepravsky.sm.uikit.theme.AppTheme
import be.nepravsky.sm.uikit.theme.colors.rightLeftGradient
import be.nepravsky.sm.uikit.view.text.TextBold
import be.nepravsky.sm.uikit.view.textfield.CBaseTextField
import be.nepravsky.sm.uikit.view.textfield.numberKeyboardOptions
import coil.compose.AsyncImage


@Composable
fun ProjectItemView(
    modifier: Modifier = Modifier,
    id: Long,
    name: String,
    run: String,
    onRunChanged: (run: String) -> Unit,
) {

    Row(
        modifier = modifier
            .wrapContentHeight()
            .fillMaxWidth()
            .padding(top = AppTheme.padding.s_4)
            .clip(RoundedCornerShape(AppTheme.radius.r_8))
            .background(rightLeftGradient)
            .border(
                AppTheme.viewSize.border_small,
                AppTheme.colors.foreground_hard,
                RoundedCornerShape(AppTheme.radius.r_8)
            ),
        verticalAlignment = Alignment.CenterVertically,
    ) {
        AsyncImage(
            modifier = Modifier
                .padding(vertical = AppTheme.padding.s_8, horizontal = AppTheme.padding.s_8)
                .clip(RoundedCornerShape(AppTheme.radius.r_2))
                .size(AppTheme.viewSize.icon_normal),
            model = getItemImageURL(itemId = id),
            contentDescription = null,
            //TODO change placeholder
            placeholder = rememberVectorPainter(Icons.Default.Close)
        )

        CBaseTextField(
            modifier = Modifier
                .wrapContentSize()
                .width(48.dp),
            value = run,
            onValueChange = { runs -> onRunChanged(runs) },
            keyboardOptions = numberKeyboardOptions,
            textAlign = TextAlign.Center,
        )

        TextBold(
            modifier = Modifier.padding(start = AppTheme.padding.s_8),
            text = name,
            maxLines = 1,
        )
    }
}