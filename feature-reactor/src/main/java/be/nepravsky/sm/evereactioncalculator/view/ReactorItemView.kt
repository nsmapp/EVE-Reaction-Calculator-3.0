package be.nepravsky.sm.evereactioncalculator.view

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
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
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.vector.rememberVectorPainter
import androidx.compose.ui.res.stringResource
import be.nepravsky.sm.evereactioncalculator.model.ReactionItemModel
import be.nepravsky.sm.evereactioncalculator.uikit.R
import be.nepravsky.sm.evereactioncalculator.utils.getItemImageURL
import be.nepravsky.sm.uikit.theme.AppTheme
import be.nepravsky.sm.uikit.view.row.KeyValueRow
import be.nepravsky.sm.uikit.view.text.TextBold
import coil.compose.AsyncImage

//TODO add price update time
@Composable
fun ReactorItemView(
    item: ReactionItemModel,
    gradient1: Brush,
    gradient2: Brush,
) {
    var isFullInfoVisible by remember { mutableStateOf(false) }
    val startPadding = if (item.isProduct) AppTheme.padding.zero else AppTheme.padding.s_8

    val background = if (item.isProduct) gradient1 else gradient2
    val borderColor =
        if (item.hasZeroPrice) AppTheme.colors.warning else AppTheme.colors.foreground_hard

    Column(
        modifier = Modifier
            .padding(horizontal = AppTheme.padding.s_2)
            .padding(start = startPadding)
            .wrapContentHeight()
            .fillMaxWidth()
            .padding(top = AppTheme.padding.s_2)
            .clip(RoundedCornerShape(AppTheme.radius.r_8))
            .background(background)
            .clickable { isFullInfoVisible = isFullInfoVisible.not() }
            .border(
                AppTheme.viewSize.border_small,
                borderColor,
                RoundedCornerShape(AppTheme.radius.r_8)
            ),
    ) {
        Row(
            modifier = Modifier
                .wrapContentHeight()
                .fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
        ) {
            AsyncImage(
                modifier = Modifier
                    .padding(
                        vertical = AppTheme.padding.s_4, horizontal = AppTheme.padding.s_4
                    )
                    .clip(RoundedCornerShape(AppTheme.radius.r_2))
                    .size(AppTheme.viewSize.icon_small),
                model = getItemImageURL(itemId = item.id),
                contentDescription = null,
                //TODO change placeholder
                placeholder = rememberVectorPainter(Icons.Default.Close)
            )
            TextBold(
                text = "${item.quantity} x ${item.name}",
                maxLines = 2,
            )

        }

        AnimatedVisibility(
            visible = isFullInfoVisible,
            modifier = Modifier
                .padding(horizontal = AppTheme.padding.s_4)
                .padding(bottom = AppTheme.padding.s_2)
        ) {
            Column {
                KeyValueRow(
                    modifier = Modifier.fillMaxWidth(),
                    key = stringResource(R.string.feature_reactor_volume),
                    value = item.volume,
                    style = AppTheme.typography.medium,
                )

                KeyValueRow(
                    modifier = Modifier.fillMaxWidth(),
                    key = stringResource(R.string.feature_reactor_sell_price),
                    value = item.sell,
                    style = AppTheme.typography.medium,
                )

                KeyValueRow(
                    modifier = Modifier.fillMaxWidth(),
                    key = stringResource(R.string.feature_reactor_buy_price),
                    value = item.buy,
                    style = AppTheme.typography.medium,
                )

                KeyValueRow(
                    modifier = Modifier.fillMaxWidth(),
                    key = stringResource(R.string.feature_reactor_price_updated),
                    value = item.updateTime,
                    style = AppTheme.typography.medium,
                )

            }
        }

    }
}