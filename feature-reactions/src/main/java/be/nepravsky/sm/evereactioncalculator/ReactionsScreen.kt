package be.nepravsky.sm.evereactioncalculator

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import be.nepravsky.sm.evereactioncalculator.uikit.R
import be.nepravsky.sm.evereactioncalculator.utils.TEXT_EMPTY
import be.nepravsky.sm.evereactioncalculator.view.Blueprint
import be.nepravsky.sm.uikit.theme.AppTheme
import be.nepravsky.sm.uikit.view.textfield.CTextField


@Composable
fun ReactionsScreen(
    onReactionClick: () -> Unit,
    viewModel: ReactionsViewModel,
) {

    val state by viewModel.state.collectAsState()
    var searchText by remember { mutableStateOf(TEXT_EMPTY) }

    LaunchedEffect(null) { viewModel.getBpcList(searchText) }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(horizontal = AppTheme.padding.s_8),
    ) {

        //TODO add filter and search icons
        CTextField(
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = AppTheme.padding.s_8),
            value = searchText,
            onValueChange = { text ->
                searchText = text
                viewModel.getBpcList(text)
            },
            hint = stringResource(R.string.feature_reaction_search),
        )

        LazyColumn(
            modifier = Modifier.fillMaxWidth(),
            content = {
                itemsIndexed(
                    items = state.bpcShortList,
                    key = { _, item -> item.id }) { _, item ->
                    Blueprint(item = item)
                }
            }
        )
    }
}