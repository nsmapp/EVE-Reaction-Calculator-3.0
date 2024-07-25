package be.nepravsky.sm.uikit.view.blueprint

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
import androidx.compose.material.icons.twotone.FavoriteBorder
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import be.nepravsky.sm.evereactioncalculator.uikit.R
import be.nepravsky.sm.uikit.theme.AppTheme
import be.nepravsky.sm.uikit.utils.toTime
import be.nepravsky.sm.uikit.view.row.KeyValueRow
import be.nepravsky.sm.uikit.view.text.TextBold


@Composable
fun Blueprint(
    modifier: Modifier = Modifier,
    name: String,
    buildTimeSeconds: Long,
    runCount: Long,
    icon: ImageVector,
) {
    val day = stringResource(R.string.time_day_short)
    val hour = stringResource(R.string.time_hour_short)
    val min = stringResource(R.string.time_min_short)
    val sec = stringResource(R.string.time_sec_short)


    val time by remember(buildTimeSeconds) {
        derivedStateOf {
            val t = buildTimeSeconds.toTime()
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
            .background(
                brush = Brush.horizontalGradient(
                    listOf(
                        AppTheme.accentColor,
                        AppTheme.colors.default_accent_second
                    )
                ),
                shape = RoundedCornerShape(AppTheme.radius.r_8),
            )
            .border(
                AppTheme.viewSize.border_small,
                AppTheme.accentColor,
                RoundedCornerShape(AppTheme.radius.r_8)
            )
            .padding(AppTheme.padding.s_4)
        ,
        verticalAlignment = Alignment.CenterVertically,
    ) {
        Icon(
            modifier = Modifier
                .clip(RoundedCornerShape(AppTheme.radius.r_2))
                .padding(vertical = AppTheme.padding.s_8, horizontal = AppTheme.padding.s_16)
                .size(AppTheme.viewSize.icon_24),
            imageVector = icon,
            contentDescription = null
        )
        Column(
            Modifier
                .fillMaxWidth()
                .wrapContentHeight()
        ) {

            TextBold(text = name)

            KeyValueRow(
                modifier = Modifier
                    .padding(end = AppTheme.padding.s_8)
                    .fillMaxWidth()
                    .wrapContentHeight(),
                key = stringResource(R.string.view_blueprint_base_reaction_time),
                value = time,
            )
            KeyValueRow(
                modifier = Modifier
                    .padding(end = AppTheme.padding.s_8)
                    .fillMaxWidth()
                    .wrapContentHeight(),
                key = stringResource(R.string.view_blueprint_unit_per_run),
                value = runCount,
            )

        }
    }
}


@Preview
@Composable
fun BlueprintPreview(){
    AppTheme{
        Column {
            Blueprint(
                name = "Tengu",
                buildTimeSeconds = 10,
                runCount = 1,
                icon = Icons.TwoTone.FavoriteBorder
            )

            Blueprint(
                name = "Tengu",
                buildTimeSeconds = 100,
                runCount = 1,
                icon = Icons.TwoTone.FavoriteBorder
            )
            Blueprint(
                name = "Tengu",
                buildTimeSeconds = 1000,
                runCount = 1,
                icon = Icons.TwoTone.FavoriteBorder
            )
            Blueprint(
                name = "Tengu",
                buildTimeSeconds = 10000,
                runCount = 1,
                icon = Icons.TwoTone.FavoriteBorder
            )
            Blueprint(
                name = "Tengu",
                buildTimeSeconds = 1000000,
                runCount = 1,
                icon = Icons.TwoTone.FavoriteBorder
            )
        }

    }
}