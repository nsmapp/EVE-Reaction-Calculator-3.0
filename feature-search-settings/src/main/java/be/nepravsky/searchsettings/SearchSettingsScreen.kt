package be.nepravsky.searchsettings

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Refresh
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import be.nepravsky.sm.evereactioncalculator.uikit.R
import be.nepravsky.searchsettings.contract.SearchSettingsRouter
import be.nepravsky.searchsettings.model.SearchSettingsState
import be.nepravsky.searchsettings.view.ReactionGroupItem
import be.nepravsky.sm.uikit.theme.AppTheme
import be.nepravsky.sm.uikit.view.appbar.CAppBar
import kotlinx.coroutines.flow.StateFlow


@Composable
fun SearchSettingsScreen(
    viewModel: SearchSettingsViewModel,
    router: SearchSettingsRouter,
) {

    LaunchedEffect(null) {
        viewModel.getReactionGroups()
    }

    val onReactionGroupClick: (Long, Boolean) -> Unit = remember(viewModel) {
        {groupId, isSelected -> viewModel.onReactionGroupClick(groupId, isSelected) }
    }
    val onClearFilterClick: () -> Unit = remember(viewModel) { { viewModel.cleanFilter() } }
    val onFinishClick: () -> Unit = remember(router) { { router.onFinish() } }

    SearchScreenView(
        onBackClick = onFinishClick,
        onReactionGroupClick = onReactionGroupClick,
        onClearFilterClick = onClearFilterClick,
        state = viewModel.state
    )
}

@Composable
private fun SearchScreenView(
    onBackClick: () -> Unit,
    onReactionGroupClick: (Long, Boolean) -> Unit,
    onClearFilterClick: () -> Unit,
    state: StateFlow<SearchSettingsState>,
) {
    Column(
        modifier = Modifier
            .background(color = AppTheme.colors.foreground)
            .fillMaxSize()
    ) {
        CAppBar(
            modifier = Modifier,
            text = stringResource(R.string.feature_search_settings_search_settings),
            onBackPressed = onBackClick,
            actionIcon = Icons.Default.Refresh,
            onActionClick = onClearFilterClick,
        )

        val viewState: SearchSettingsState by state.collectAsStateWithLifecycle()

        LazyColumn(
            modifier = Modifier,
            content = {
                itemsIndexed(
                    items = viewState.reactionGroups,
                    key = { _, item -> item.id }) { _, item ->

                    ReactionGroupItem(
                        item = item,
                        onReactionGroupClick = onReactionGroupClick
                    )
                }

            }
        )
    }
}