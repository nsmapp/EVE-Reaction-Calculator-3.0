package be.belveb.searchsettings

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import be.nepravsky.sm.evereactioncalculator.uikit.R
import be.belveb.searchsettings.contract.SearchSettingsRouter
import be.belveb.searchsettings.view.ReactionGroupItem
import be.nepravsky.sm.uikit.theme.AppTheme
import be.nepravsky.sm.uikit.view.appbar.CAppBar


@Composable
fun SearchSettingsScreen(
    viewModel: SearchSettingsViewModel,
    router: SearchSettingsRouter,
) {

    val state by viewModel.state.collectAsState()

    LaunchedEffect(null) {
        viewModel.getReactionGroups()
    }

    Column(
        modifier = Modifier
            .background(color = AppTheme.colors.foreground)
            .fillMaxSize()
    ) {
        CAppBar(
            modifier = Modifier,
            text = stringResource(R.string.feature_search_settings_search_settings),
            onBackPressed = { router.onFinish() }
        )
        LazyColumn(
            modifier = Modifier,
            content = {
                itemsIndexed(
                    items = state.reactionGroups,
                    key = { _, item -> item.id }) { _, item ->

                    ReactionGroupItem(
                        item = item,
                        onItemClick = { isSelected, groupId ->
                            viewModel.onReactionGroupClick(groupId, isSelected)
                        }
                    )
                }

            }
        )
    }
}