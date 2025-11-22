package be.nepravsky.sm.evereactioncalculator.reactions

import androidx.compose.animation.core.tween
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.FilterAlt
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.filled.Settings
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import be.nepravsky.sm.evereactioncalculator.reactions.contract.ReactionsRouter
import be.nepravsky.sm.uikit.view.items.BlueprintItem
import be.nepravsky.sm.evereactioncalculator.uikit.R
import be.nepravsky.sm.evereactioncalculator.utils.TEXT_EMPTY
import be.nepravsky.sm.uikit.theme.AppTheme
import be.nepravsky.sm.uikit.view.textfield.CTextField


@OptIn(ExperimentalFoundationApi::class)
@Composable
fun ReactionsScreen(
    viewModel: ReactionsViewModel,
    router: ReactionsRouter,
) {

    val state by viewModel.state.collectAsState()
    var searchText by remember { mutableStateOf(TEXT_EMPTY) }

    LaunchedEffect(null) { viewModel.getBpcList(searchText) }

    Column(
        modifier = Modifier
            .background(AppTheme.colors.foreground)
            .padding(horizontal = AppTheme.padding.s_8)
            ,
        verticalArrangement = Arrangement.SpaceBetween,
    ) {

        LazyColumn(
            modifier = Modifier
                .weight(1f)
            ,
            content = {
                itemsIndexed(
                    items = state.bpcShortList,
                    key = { _, item -> item.id }) { _, item ->
                    BlueprintItem(
                        modifier = Modifier
                            .animateItem(tween())
                        ,
                        id = item.id,
                        name = item.name,
                        baseTime = item.baseTime,
                        onItemClick = {reactionId ->  router.buildReaction(reactionId, true)}
                    )
                }
            }
        )

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
            trailingIcon = Icons.Default.FilterAlt,
            onTrailingClick = { router.openSearchSettings() },
            hint = stringResource(R.string.feature_reaction_search),
        )
    }
}