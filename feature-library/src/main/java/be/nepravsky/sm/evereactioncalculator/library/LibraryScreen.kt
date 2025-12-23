package be.nepravsky.sm.evereactioncalculator.library

import androidx.compose.animation.animateContentSize
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
import androidx.compose.foundation.layout.padding
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
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.unit.IntOffset
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import be.nepravsky.sm.evereactioncalculator.library.model.ProjectModel
import be.nepravsky.sm.evereactioncalculator.library.view.ProjectItemView
import be.nepravsky.sm.evereactioncalculator.library.view.SwipeMenuView
import be.nepravsky.sm.uikit.theme.AppTheme
import be.nepravsky.sm.uikit.view.icons.NormalIcon
import kotlinx.collections.immutable.ImmutableList
import kotlinx.coroutines.launch
import org.koin.androidx.compose.koinViewModel
import kotlin.math.roundToInt

@OptIn(ExperimentalMaterialApi::class, ExperimentalFoundationApi::class)
@Composable
fun LibraryScreen(
    router: LibraryRouter,
) {

    val viewModel: LibraryViewModel = koinViewModel()

    LaunchedEffect(Unit) {
        viewModel.getAllWithoutItems()
    }

    val state by viewModel.state.collectAsStateWithLifecycle()


    LibraryScreenContent(
        projects = state.projects,
        onAddProject = remember(router) { router::addProject },
        onEditProject = remember(router) { router::editProject },
        onRunProject = remember(router) { router::runProject },
        onDeleteProject = remember(viewModel) { viewModel::deleteProject},
    )
}

@Composable
@OptIn(ExperimentalMaterialApi::class)
private fun LibraryScreenContent(
    projects: ImmutableList<ProjectModel>,
    onAddProject: (Long?) -> Unit,
    onEditProject: (Long) -> Unit,
    onRunProject: (Long) -> Unit,
    onDeleteProject: (Long) -> Unit,
) {
    val scope = rememberCoroutineScope()
    val sizePx = with(LocalDensity.current) { remember { 90.dp.toPx() } }
    val anchors = remember { mapOf(0f to 0, -sizePx to 1) }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(color = AppTheme.colors.foreground)
            .padding(horizontal = AppTheme.padding.s_8)
    ) {

        LazyColumn(
            modifier = Modifier.animateContentSize(),
            content = {
                itemsIndexed(
                    items = projects,
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
                                onDeleteProject(item.id)
                            },
                            onEditClick = {
                                scope.launch { swipeableState.animateTo(0, tween()) }
                                onEditProject(item.id)
                            },
                        )

                        ProjectItemView(
                            modifier = Modifier
                                .offset { IntOffset(swipeableState.offset.value.roundToInt(), 0) },
                            item = item,
                            onItemClick = onRunProject
                        )
                    }
                }

                stickyHeader {

                    NormalIcon(
                        modifier = Modifier
                            .fillMaxWidth()
                            .clickable { onAddProject(null) },
                        imageVector = Icons.Default.Add,
                        colorFilter = ColorFilter.tint(AppTheme.colors.text)
                    )

                }
            }
        )

    }
}