package be.belveb.builder

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.filled.Settings
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import be.nepravsky.sm.evereactioncalculator.uikit.R
import be.nepravsky.sm.evereactioncalculator.utils.TEXT_EMPTY
import be.nepravsky.sm.uikit.theme.AppTheme
import be.nepravsky.sm.uikit.view.textfield.CTextField

@Composable
fun BuilderScreen(
    viewModel: BuilderViewModel,
    router: BuilderRouter
) {

    var searchText by remember { mutableStateOf(TEXT_EMPTY) }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(AppTheme.colors.foreground)
            .padding(horizontal = AppTheme.padding.s_8),
        verticalArrangement = Arrangement.SpaceBetween,
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
            trailingIcon = Icons.Default.Settings,
            onTrailingClick = { router.openSearchSettings() },
            hint = stringResource(R.string.feature_reaction_search),
        )
    }
}