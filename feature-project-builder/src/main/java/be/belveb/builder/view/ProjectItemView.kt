package be.belveb.builder.view

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.vector.rememberVectorPainter
import be.nepravsky.sm.evereactioncalculator.utils.getItemImageURL
import be.nepravsky.sm.uikit.theme.AppTheme
import be.nepravsky.sm.uikit.theme.colors.gradient1
import be.nepravsky.sm.uikit.view.text.TextBold
import coil.compose.AsyncImage


@Composable
fun ProjectItemView(
    modifier: Modifier = Modifier,
    id: Long,
    name: String,
) {


    Row(
        modifier = modifier
            .wrapContentHeight()
            .fillMaxWidth()
            .padding(top = AppTheme.padding.s_4)
            .clip(RoundedCornerShape(AppTheme.radius.r_8))
            .background(gradient1)
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

        Column(
            Modifier
                .fillMaxWidth()
                .wrapContentHeight()
        ) {

            TextBold(
                text = name,
                maxLines = 1,
            )
        }
    }
}