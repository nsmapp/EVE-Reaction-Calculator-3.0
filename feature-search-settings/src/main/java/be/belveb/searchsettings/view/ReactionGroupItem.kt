package be.belveb.searchsettings.view

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import coil.compose.AsyncImage
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.vector.rememberVectorPainter
import be.nepravsky.sm.domain.model.ReactionGroup
import be.nepravsky.sm.evereactioncalculator.utils.getItemImageURL
import be.nepravsky.sm.uikit.theme.AppTheme
import be.nepravsky.sm.uikit.view.checkbox.CSwitch
import be.nepravsky.sm.uikit.view.text.TextMediumSmall


@Composable
fun ReactionGroupItem(
    modifier: Modifier = Modifier,
    item: ReactionGroup,
    onItemClick: (isSelected: Boolean, groupId: Long) -> Unit,
) {
    val group by remember(key1 = item.id, key2 = item.isSelected) { mutableStateOf(item) }

    Row(
        modifier = modifier
            .wrapContentHeight()
            .fillMaxWidth()
            .clickable{onItemClick(group.isSelected.not(), group.id)}
            .background(color = AppTheme.colors.foreground)
            .padding(horizontal = AppTheme.padding.s_16),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Row {
            AsyncImage(
                modifier = Modifier
                    .clickable { onItemClick(group.isSelected.not(), group.id) }
                    .clip(RoundedCornerShape(AppTheme.radius.r_2))
                    .padding(end = AppTheme.padding.s_16)
                    .size(AppTheme.viewSize.icon_small),
                model = getItemImageURL(itemId = item.iconId),
                contentDescription = null,
                //TODO change placeholder
                placeholder = rememberVectorPainter(Icons.Default.Close)
            )

            TextMediumSmall(
                modifier = Modifier,
                text = item.name,
                maxLines = 2,
            )
        }

        CSwitch(
            checked = group.isSelected,
            onCheckedChange = { checked -> onItemClick(checked, group.id) },
        )
    }
}
