package be.nepravsky.builder.view

import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.SheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import be.nepravsky.builder.model.BpcShortModel
import be.nepravsky.sm.evereactioncalculator.uikit.R
import be.nepravsky.sm.uikit.theme.AppTheme
import be.nepravsky.sm.uikit.view.items.BlueprintItem
import be.nepravsky.sm.uikit.view.textfield.CTextField
import kotlinx.collections.immutable.ImmutableList

@Composable
@OptIn(ExperimentalMaterial3Api::class)
fun ReactionsBottomSheat(
    sheetState: SheetState,
    searchText: String,
    reactions: ImmutableList<BpcShortModel>,
    onDismissRequest: () -> Unit,
    addProjectItem: (item: BpcShortModel) -> Unit,
    onSearchBpc:(text: String) -> Unit,
) {

    LaunchedEffect(Unit) {
        onSearchBpc(searchText)
    }

    ModalBottomSheet(
        sheetState = sheetState,
        onDismissRequest = onDismissRequest
    ) {

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
                onValueChange = onSearchBpc,
                leadingIcon = Icons.Default.Search,
                hint = stringResource(R.string.feature_reaction_search),
            )

            LazyColumn(
                modifier = Modifier
                    .weight(1f),
                content = {
                    itemsIndexed(
                        items = reactions,
                        key = { _, item -> item.id }) { _, item ->
                        BlueprintItem(
                            modifier = Modifier.animateItem(tween()),
                            id = item.id,
                            name = item.name,
                            baseTime = item.baseTime,
                            onItemClick = { addProjectItem(item) }
                        )
                    }
                }
            )
        }
    }
}