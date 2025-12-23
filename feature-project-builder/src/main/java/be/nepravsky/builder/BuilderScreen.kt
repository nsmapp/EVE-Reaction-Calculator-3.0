package be.nepravsky.builder

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Done
import androidx.compose.material.icons.filled.FilterAlt
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import be.nepravsky.builder.model.ProjectBuildSideEffect
import be.nepravsky.builder.view.ProjectItemsView
import be.nepravsky.builder.view.ReactionsBottomSheat
import be.nepravsky.sm.evereactioncalculator.uikit.R
import be.nepravsky.sm.uikit.theme.AppTheme
import be.nepravsky.sm.uikit.view.appbar.CAppBar
import be.nepravsky.sm.uikit.view.textfield.CTextField
import org.koin.androidx.compose.koinViewModel
import org.koin.core.parameter.parametersOf

@OptIn(
    ExperimentalFoundationApi::class,
    ExperimentalMaterial3Api::class,
    ExperimentalMaterialApi::class
)
@Composable
fun BuilderScreen(
    projectId: Long?, router: BuilderRouter
) {
    val viewModel = koinViewModel<BuilderViewModel>(
        key = BuilderViewModel::class.simpleName + projectId,
        parameters = { parametersOf(projectId) })

    val state by viewModel.state.collectAsStateWithLifecycle()
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

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(AppTheme.colors.foreground)
            .padding(horizontal = AppTheme.padding.s_8),
    ) {

        CAppBar(
            text = stringResource(R.string.feature_project_builder),
            onBackPressed = remember(router) { router::onBackPressed },
            onActionClick = remember(viewModel) { viewModel::saveProject },
            actionIcon = Icons.Default.Done,
        )

        CTextField(
            modifier = Modifier
                .padding(bottom = AppTheme.padding.s_16)
                .fillMaxWidth(),
            value = state.name,
            onValueChange = remember(viewModel) { viewModel::setProjectName },
            trailingIcon = Icons.Default.FilterAlt,
            onTrailingClick = remember(router) { router::openSearchSettings },
            hint = stringResource(R.string.feature_project_builder_project_name),
        )

        ProjectItemsView(
            items = state.items,
            onDeleteItem = remember(viewModel) { viewModel::deleteProjectItem },
            onSetRunCount = remember(viewModel) { viewModel::setRunCount },
            onShowReactionsSheet = remember(viewModel) { viewModel::showTypeBottomSheet })
    }

    if (state.isShowTypeBottomSheet) {
        ReactionsBottomSheat(
            sheetState = sheetState,
            searchText = state.searchText,
            reactions = state.types,
            onDismissRequest = remember(viewModel) { viewModel::hideTypeBottomSheet },
            addProjectItem = remember(viewModel) { viewModel::addProjectItem },
            onSearchBpc = remember(viewModel) { viewModel::searchReactions })
    }

}

