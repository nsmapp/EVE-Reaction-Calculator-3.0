package be.nepravsky.sm.evereactioncalculator.projects.view

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
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
import be.nepravsky.sm.evereactioncalculator.projects.model.ProjectModel
import be.nepravsky.sm.evereactioncalculator.utils.getItemImageURL
import be.nepravsky.sm.uikit.theme.AppTheme
import be.nepravsky.sm.uikit.theme.colors.rightLeftGradient
import be.nepravsky.sm.uikit.view.text.TextBold
import coil.compose.AsyncImage

@Composable
fun ProjectItemView(
    modifier: Modifier,
    item: ProjectModel,
    onItemClick: () -> Unit
) {
    Row(
        modifier = modifier
            .wrapContentHeight()
            .fillMaxWidth()
            .padding(top = AppTheme.padding.s_4)
            .clip(RoundedCornerShape(AppTheme.radius.r_8))
            .background(rightLeftGradient)
            .clickable { onItemClick() }
            .border(
                AppTheme.viewSize.border_small,
                AppTheme.colors.foreground_hard,
                RoundedCornerShape(AppTheme.radius.r_8)
            ),
        verticalAlignment = Alignment.CenterVertically
    ) {
        AsyncImage(
            modifier = Modifier
                .padding(AppTheme.padding.s_8)
                .clip(RoundedCornerShape(AppTheme.radius.r_2))
                .size(AppTheme.viewSize.icon_normal),
            model = getItemImageURL(itemId = item.iconId),
            contentDescription = null,
            //TODO change placeholder
            placeholder = rememberVectorPainter(Icons.Default.Close)
        )
        TextBold(
            modifier = Modifier.padding(start = AppTheme.padding.s_8),
            text = item.name,
            maxLines = 2,
        )
    }
}