package be.nepravsky.sm.evereactioncalculator

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import be.nepravsky.sm.evereactioncalculator.view.ReactionInformationView
import be.nepravsky.sm.evereactioncalculator.view.ReactorItemView
import be.nepravsky.sm.uikit.theme.AppTheme
import be.nepravsky.sm.uikit.theme.colors.gradient1
import be.nepravsky.sm.uikit.theme.colors.gradient2
import be.nepravsky.sm.uikit.view.text.TextMedium


@Composable
fun ReactorScreen(
    viewModel: ReactorViewModel,
    router: ReactorRouter,
) {
    val state = viewModel.state.collectAsState()
    var isShowReactionInformation by remember { mutableStateOf(true) }

    Column {

        Column(
            modifier = Modifier
                .fillMaxWidth()
                .wrapContentHeight()
                .padding(AppTheme.padding.s_2)
                .border(
                    BorderStroke(
                        AppTheme.viewSize.border_small,
                        AppTheme.colors.foreground_hard
                    ), RoundedCornerShape(AppTheme.radius.r_8)
                )
                .background(
                    AppTheme.colors.foreground_light,
                    RoundedCornerShape(AppTheme.radius.r_4)
                )
        ) {
            Row {
                TextMedium(modifier = Modifier.clickable {
                    isShowReactionInformation = isShowReactionInformation.not()
                }, text = "Minimize")
            }

            AnimatedVisibility(
                modifier = Modifier.padding(AppTheme.padding.s_4),
                visible = isShowReactionInformation,
            ) {
                ReactionInformationView(state)
            }
        }


        LazyColumn(modifier = Modifier, content = {
            itemsIndexed(items = state.value.data.items, key = { _, item -> item.id }) { _, item ->
                ReactorItemView(item, gradient1, gradient2)
            }
        })
    }


}