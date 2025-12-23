package be.nepravsky.sm.evereactioncalculator.reactions

import androidx.compose.animation.core.tween
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.FilterAlt
import androidx.compose.material.icons.filled.Search
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import be.nepravsky.sm.evereactioncalculator.reactions.contract.ReactionsRouter
import be.nepravsky.sm.evereactioncalculator.reactions.model.BpcShortModel
import be.nepravsky.sm.evereactioncalculator.uikit.R
import be.nepravsky.sm.uikit.theme.AppTheme
import be.nepravsky.sm.uikit.view.items.BlueprintItem
import be.nepravsky.sm.uikit.view.text.TextMediumLarge
import be.nepravsky.sm.uikit.view.textfield.CTextField
import kotlinx.collections.immutable.ImmutableList
import org.koin.androidx.compose.koinViewModel


@OptIn(ExperimentalFoundationApi::class)
@Composable
fun ReactionsScreen(
    router: ReactionsRouter,
) {

    val viewModel = koinViewModel<ReactionsViewModel>()
    val state by viewModel.state.collectAsStateWithLifecycle()

    LaunchedEffect(Unit) { viewModel.onScreeOpen() }

    ReactionsContent(
        searchText = state.searchText,
        bpcShortList = state.bpcShortList,
        onSearchReaction = remember(viewModel) {viewModel::searchReactions  },
        onBuildReaction = router::buildReaction,
        onOpenSearchSettings = router::openSearchSettings,
    )
}

@Composable
private fun ReactionsContent(
    searchText: String,
    bpcShortList: ImmutableList<BpcShortModel>,
    onSearchReaction: (String) -> Unit,
    onBuildReaction: (reactionId: Long) -> Unit,
    onOpenSearchSettings: () -> Unit,
) {

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(AppTheme.colors.foreground)
            .padding(horizontal = AppTheme.padding.s_8),
        verticalArrangement = Arrangement.SpaceBetween,
    ) {

        if (bpcShortList.isEmpty()) {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .weight(1f),
                contentAlignment = Alignment.Center
            ) {
                TextMediumLarge(
                    text = R.string.feature_reactions_bpc_not_found,
                    color = AppTheme.colors.text_ligth
                )
            }

        } else {
            LazyColumn(
                modifier = Modifier
                    .weight(1f),
                content = {
                    itemsIndexed(
                        items = bpcShortList,
                        key = { _, item -> item.id }) { _, item ->
                        BlueprintItem(
                            modifier = Modifier
                                .animateItem(tween()),
                            id = item.id,
                            name = item.name,
                            baseTime = item.baseTime,
                            onItemClick = { reactionId ->
                                onBuildReaction(reactionId)
                            }
                        )
                    }
                }
            )
        }

        CTextField(
            modifier = Modifier
                .fillMaxWidth(),
            value = searchText,
            onValueChange = onSearchReaction,
            leadingIcon = Icons.Default.Search,
            trailingIcon = Icons.Default.FilterAlt,
            onTrailingClick = onOpenSearchSettings,
            hint = stringResource(R.string.feature_reaction_search),
        )
    }
}