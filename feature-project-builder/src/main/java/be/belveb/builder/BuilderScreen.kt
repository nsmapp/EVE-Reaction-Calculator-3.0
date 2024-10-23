package be.belveb.builder

import androidx.compose.animation.animateContentSize
import androidx.compose.animation.core.tween
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material.icons.filled.ThumbUp
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.res.stringResource
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import be.belveb.builder.model.ProjectBuildSideEffect
import be.belveb.builder.view.ProjectItemView
import be.nepravsky.sm.evereactioncalculator.uikit.R
import be.nepravsky.sm.evereactioncalculator.utils.TEXT_EMPTY
import be.nepravsky.sm.uikit.theme.AppTheme
import be.nepravsky.sm.uikit.view.appbar.CAppBar
import be.nepravsky.sm.uikit.view.icons.NormalIcon
import be.nepravsky.sm.uikit.view.items.BlueprintItem
import be.nepravsky.sm.uikit.view.textfield.CTextField

@OptIn(ExperimentalFoundationApi::class, ExperimentalMaterial3Api::class)
@Composable
fun BuilderScreen(
    viewModel: BuilderViewModel,
    router: BuilderRouter
) {

    val state = viewModel.state.collectAsStateWithLifecycle()

    val sheetState = rememberModalBottomSheetState(
        skipPartiallyExpanded = true
    )

    LaunchedEffect(null) {
        viewModel.sideEffect.collect { effect ->
            when (effect) {
                is ProjectBuildSideEffect.CLOSE -> router.onBackPressed()
            }
        }
    }

    LaunchedEffect(null) { viewModel.initData() }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(AppTheme.colors.foreground)
            .padding(horizontal = AppTheme.padding.s_8),
    ) {

        CAppBar(
            text = stringResource(R.string.feature_project_builder),
            onBackPressed = { router.onBackPressed() }
        )

        CTextField(
            modifier = Modifier
                .padding(bottom = AppTheme.padding.s_16)
                .fillMaxWidth(),
            value = state.value.name,
            onValueChange = { text ->
                viewModel.setProjectName(text)
            },
            //TODO replace icon
            trailingIcon = Icons.Default.Settings,
            onTrailingClick = { router.openSearchSettings() },
            leadingIcon = Icons.Default.ThumbUp,
            onLeadingClick = { viewModel.saveProject() },
            hint = stringResource(R.string.feature_project_builder_project_name),
        )


        Column(
            modifier = Modifier
                .background(color = AppTheme.colors.foreground)
                .fillMaxSize(),
        ) {

            LazyColumn(modifier = Modifier.animateContentSize(
                animationSpec = tween(durationMillis = 500)
            ),
                content = {

                    itemsIndexed(
                        items = state.value.items,
                        key = { _, item -> item.id }) { _, item ->

                        ProjectItemView(
                            modifier = Modifier,
                            id = item.id,
                            name = item.name,
                            run = item.runCount,
                            onRunChanged = {run ->  viewModel.setRunCount(run, item.id)}
                        )
                    }

                    stickyHeader {

                        NormalIcon(
                            modifier = Modifier
                                .fillMaxWidth()
                                .clickable { viewModel.showTypeBottomSheet() },
                            imageVector = Icons.Default.Add,
                            colorFilter = ColorFilter.tint(AppTheme.colors.text)
                        )

                    }
                })
        }
    }

    if (state.value.isShowTypeBottomSheet) ModalBottomSheet(
        sheetState = sheetState,
        onDismissRequest = { viewModel.hideTypeBottomSheet() }
    ) {

        var searchText by remember { mutableStateOf(TEXT_EMPTY) }

        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(AppTheme.colors.foreground)
                .padding(horizontal = AppTheme.padding.s_8),
        ) {

            //TODO add filter and search icons
            CTextField(
                modifier = Modifier
                    .padding(bottom = AppTheme.padding.s_16)
                    .fillMaxWidth(),
                value = searchText,
                onValueChange = { text ->
                    searchText = text
                    viewModel.getBpcList(text)
                },
                leadingIcon = Icons.Default.Search,
                hint = stringResource(R.string.feature_reaction_search),
            )

            LazyColumn(
                modifier = Modifier
                    .weight(1f),
                content = {
                    itemsIndexed(
                        items = state.value.types,
                        key = { _, item -> item.id }) { _, item ->
                        BlueprintItem(
                            modifier = Modifier.animateItemPlacement(animationSpec = tween()),
                            id = item.id,
                            name = item.name,
                            baseTime = item.baseTime,
                            onItemClick = { reactionId -> viewModel.addProjectItem(item) }
                        )
                    }
                }
            )
        }
    }
}