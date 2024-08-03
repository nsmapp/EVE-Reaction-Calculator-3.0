package be.nepravsky.sm.evereactioncalculator.view

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
import coil.compose.AsyncImage
import androidx.compose.runtime.Composable
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.vector.rememberVectorPainter
import androidx.compose.ui.res.stringResource
import be.nepravsky.sm.domain.model.BpcShort
import be.nepravsky.sm.domain.utils.toTime
import be.nepravsky.sm.evereactioncalculator.uikit.R
import be.nepravsky.sm.evereactioncalculator.utils.getItemImageURL
import be.nepravsky.sm.uikit.theme.AppTheme
import be.nepravsky.sm.uikit.view.row.KeyValueRow
import be.nepravsky.sm.uikit.view.text.TextBold


@Composable
fun Blueprint(
    modifier: Modifier = Modifier,
    item: BpcShort,
) {
    val day = stringResource(R.string.time_day_short)
    val hour = stringResource(R.string.time_hour_short)
    val min = stringResource(R.string.time_min_short)
    val sec = stringResource(R.string.time_sec_short)

    val time by remember(item.baseTime) {
        derivedStateOf {
            val t = item.baseTime.toTime()
            var  time = ""
            if (t.day > 0) time += "${t.day}$day"
            if (t.hour > 0) time += " ${t.hour}$hour"
            if (t.min > 0) time += " ${t.min}$min"
            if (t.sec > 0) time += " ${t.sec}$sec"
            time
        }
    }

    Row(
        modifier = modifier
            .wrapContentHeight()
            .fillMaxWidth()
            .padding(bottom = AppTheme.padding.s_2)
            .background(
                color = AppTheme.colors.foreground,
                shape = RoundedCornerShape(AppTheme.radius.r_8),
            )
            .border(
                AppTheme.viewSize.border_small,
                AppTheme.colors.accent,
                RoundedCornerShape(AppTheme.radius.r_8)
            ),
        verticalAlignment = Alignment.CenterVertically,
    ) {
        AsyncImage(
            modifier = Modifier
                .clip(RoundedCornerShape(AppTheme.radius.r_2))
                .padding(vertical = AppTheme.padding.s_8, horizontal = AppTheme.padding.s_8)
                .size(AppTheme.viewSize.icon_normal),
            model = getItemImageURL(itemId = item.id),
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
                text = item.name,
                maxLines = 1,
            )

            KeyValueRow(
                modifier = Modifier
                    .padding(end = AppTheme.padding.s_8)
                    .fillMaxWidth()
                    .wrapContentHeight(),
                key = stringResource(R.string.view_blueprint_base_reaction_time),
                value = time,
            )

        }
    }
}
