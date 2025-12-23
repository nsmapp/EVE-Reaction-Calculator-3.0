package be.nepravsky.builder.view

import androidx.compose.animation.animateContentSize
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.gestures.Orientation
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.FractionalThreshold
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.rememberSwipeableState
import androidx.compose.material.swipeable
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.unit.IntOffset
import androidx.compose.ui.unit.dp
import be.nepravsky.builder.model.ProjectItemModel
import be.nepravsky.sm.uikit.theme.AppTheme
import be.nepravsky.sm.uikit.view.icons.NormalIcon
import kotlinx.collections.immutable.ImmutableList
import kotlinx.coroutines.launch
import kotlin.math.roundToInt

@Composable
@OptIn(ExperimentalMaterialApi::class)
fun ProjectItemsView(
    items: ImmutableList<ProjectItemModel>,
    onDeleteItem: (id: Long) -> Unit,
    onSetRunCount: (run: String, id: Long) -> Unit,
    onShowReactionsSheet: () -> Unit,
) {

    val scope = rememberCoroutineScope()
    val sizePx = with(LocalDensity.current) { 50.dp.toPx() }
    val anchors = mapOf(0f to 0, -sizePx to 1)

    Column(
        modifier = Modifier
            .background(color = AppTheme.colors.foreground)
            .fillMaxSize(),
    ) {

        LazyColumn(
            modifier = Modifier.animateContentSize(
                animationSpec = tween(durationMillis = 500)
            ), content = {

                itemsIndexed(
                    items = items, key = { _, item -> item.id }) { _, item ->

                    val swipeableState = rememberSwipeableState(0)

                    Box(
                        modifier = Modifier.swipeable(
                            state = swipeableState,
                            anchors = anchors,
                            thresholds = { _, _ -> FractionalThreshold(0.3f) },
                            orientation = Orientation.Horizontal
                        ), contentAlignment = Alignment.CenterStart
                    ) {

                        SwipeMenuView(
                            onDeleteClick = {
                                scope.launch { swipeableState.animateTo(0, tween()) }
                                onDeleteItem(item.id)
                            },
                        )

                        ProjectItemView(
                            modifier = Modifier.offset {
                                IntOffset(swipeableState.offset.value.roundToInt(), 0)
                            },
                            id = item.id,
                            name = item.name,
                            run = item.runCount,
                            onRunChanged = { run -> onSetRunCount(run, item.id) })
                    }
                }

                stickyHeader {

                    NormalIcon(
                        modifier = Modifier
                            .fillMaxWidth()
                            .clickable { onShowReactionsSheet() },
                        imageVector = Icons.Default.Add,
                        colorFilter = ColorFilter.tint(AppTheme.colors.text)
                    )

                }
            }
        )
    }
}