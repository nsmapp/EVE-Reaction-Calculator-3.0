package be.nepravsky.sm.evereactioncalculator.projects

import androidx.compose.animation.core.tween
import androidx.compose.foundation.ExperimentalFoundationApi
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
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.unit.IntOffset
import androidx.compose.ui.unit.dp
import be.nepravsky.sm.evereactioncalculator.projects.view.ProjectItemView
import be.nepravsky.sm.evereactioncalculator.projects.view.SwipeMenuView
import be.nepravsky.sm.uikit.theme.AppTheme
import be.nepravsky.sm.uikit.theme.colors.gradient1
import be.nepravsky.sm.uikit.view.icons.NormalIcon
import kotlinx.coroutines.launch
import kotlin.math.roundToInt

@OptIn(ExperimentalMaterialApi::class, ExperimentalFoundationApi::class)
@Composable
fun LibraryScreen(
    viewModel: LibraryViewModel,
    router: LibraryRouter,
) {

    LaunchedEffect(null) {
        viewModel.loadProjects()
    }

    val state = viewModel.state.collectAsState()

    val scope = rememberCoroutineScope()
    val sizePx = with(LocalDensity.current) { 80.dp.toPx() }
    val anchors = mapOf(0f to 0, -sizePx to 1)


    Column(
        modifier = Modifier
            .background(color = AppTheme.colors.foreground)
            .fillMaxSize(),
    ) {

        LazyColumn(modifier = Modifier, content = {


            itemsIndexed(
                items = state.value.projects,
                key = { _, item -> item.id }) { _, item ->

                val swipeableState = rememberSwipeableState(0)

                Box(
                    modifier = Modifier
                        .swipeable(
                            state = swipeableState,
                            anchors = anchors,
                            thresholds = { _, _ -> FractionalThreshold(0.3f) },
                            orientation = Orientation.Horizontal
                        ),
                    contentAlignment = Alignment.CenterStart
                ) {

                    SwipeMenuView(
                        onDeleteClick = {
                            scope.launch { swipeableState.animateTo(0, tween()) }
                            viewModel.deleteProject(item.id)
                        },
                        onEditClick = {
                            scope.launch { swipeableState.animateTo(0, tween()) }
                            viewModel.editProject(item.id)
                        },
                    )

                    ProjectItemView(
                        modifier = Modifier
                            .offset { IntOffset(swipeableState.offset.value.roundToInt(), 0) },
                        gradient1 = gradient1,
                        item = item,
                        onItemClick = { viewModel.runProject(item.id) }
                    )
                }
            }

            stickyHeader {

                NormalIcon(
                    modifier = Modifier
                        .fillMaxWidth()
                        .clickable { router.addProject() },
                    imageVector = Icons.Default.Add,
                    colorFilter = ColorFilter.tint(AppTheme.colors.text)
                )

            }
        })

    }
}