package be.nepravsky.sm.evereactioncalculator.view

//noinspection UsingMaterialAndMaterial3Libraries
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.PagerState
import androidx.compose.foundation.shape.RoundedCornerShape
//noinspection UsingMaterialAndMaterial3Libraries
import androidx.compose.material.Tab
//noinspection UsingMaterialAndMaterial3Libraries
import androidx.compose.material.TabRow
//noinspection UsingMaterialAndMaterial3Libraries
import androidx.compose.material.TabRowDefaults
//noinspection UsingMaterialAndMaterial3Libraries
import androidx.compose.material.TabRowDefaults.tabIndicatorOffset
import androidx.compose.runtime.Composable
import androidx.compose.runtime.State
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.res.stringResource
import be.nepravsky.sm.evereactioncalculator.model.ReactionTab
import be.nepravsky.sm.evereactioncalculator.model.ReactorState
import be.nepravsky.sm.evereactioncalculator.uikit.R
import be.nepravsky.sm.uikit.theme.AppTheme
import be.nepravsky.sm.uikit.view.text.TextBold
import be.nepravsky.sm.uikit.view.text.TextMedium
import kotlinx.coroutines.launch

@Composable
@OptIn(ExperimentalFoundationApi::class)
fun ColumnScope.ReactionPageView(
    selectedTabIndex: Int,
    pagerState: PagerState,
    state: State<ReactorState>,
    gradient1: Brush,
    gradient2: Brush
) {
    val coroutineScope = rememberCoroutineScope()

    TabRow(
        modifier = Modifier
            .padding(horizontal = AppTheme.padding.s_2)
            .clip(RoundedCornerShape(AppTheme.radius.r_4)),
        selectedTabIndex = selectedTabIndex,
        contentColor = AppTheme.colors.foreground_light,
        indicator = { tabPositions ->
            TabRowDefaults.Indicator(
                modifier = Modifier.tabIndicatorOffset(tabPositions[selectedTabIndex]),
                color = AppTheme.colors.accent
            )
        }
    ) {

        ReactionTab.entries.forEachIndexed { index, tab ->
            Tab(
                modifier = Modifier
                    .background(AppTheme.colors.foreground_light)
                    .padding(AppTheme.padding.s_8),
                selected = selectedTabIndex == index,
                onClick = {
                    coroutineScope.launch {
                        pagerState.animateScrollToPage(index)
                    }
                },
                selectedContentColor = AppTheme.colors.foreground_light,
                unselectedContentColor = AppTheme.colors.foreground_light,
            ) {
                TextBold(
                    text =
                    if (tab == ReactionTab.REACTION) stringResource(R.string.feature_reactor_reaction)
                    else stringResource(R.string.feature_reactor_base)
                )
            }
        }
    }

    HorizontalPager(
        modifier = Modifier.weight(1f),
        state = pagerState,
        verticalAlignment = Alignment.Top,
    ) { index ->
        Column(modifier = Modifier.fillMaxWidth()) {

            if (state.value.data.hasZeroPrice) TextMedium(
                modifier = Modifier.align(Alignment.CenterHorizontally),
                text = stringResource(R.string.feature_reaction_missing_information_on_some_prices),
                color = AppTheme.colors.warning,
            )

            when (index) {
                ReactionTab.REACTION.ordinal -> LazyColumn(modifier = Modifier, content = {
                    itemsIndexed(
                        items = state.value.data.items,
                        key = { _, item -> "${item.id}${item.isProduct}" }) { _, item ->
                        ReactorItemView(item, gradient1, gradient2)
                    }
                })

                ReactionTab.BASE_TYPE.ordinal -> LazyColumn(modifier = Modifier, content = {
                    itemsIndexed(
                        items = state.value.data.baseItems,
                        key = { _, item -> "${item.id}${item.isProduct}" }) { _, item ->
                        ReactorItemView(item, gradient1, gradient2)
                    }
                })

            }
        }
    }
}
