package be.nepravsky.sm.evereactioncalculator.view

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.runtime.Composable
import androidx.compose.runtime.State
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import be.nepravsky.sm.domain.utils.TEXT_EMPTY
import be.nepravsky.sm.evereactioncalculator.model.ReactorState
import be.nepravsky.sm.evereactioncalculator.uikit.R
import be.nepravsky.sm.uikit.theme.AppTheme
import be.nepravsky.sm.uikit.view.row.KeyValueRow
import be.nepravsky.sm.uikit.view.text.TextMedium

@Composable
fun ReactionInformationView(state: State<ReactorState>) {
    Column(
        modifier = Modifier
            .wrapContentHeight()
            .fillMaxWidth()
    ) {

        TextMedium(text = stringResource(R.string.feature_reactor_products))
        KeyValueRow(
            modifier = Modifier
                .fillMaxWidth()
                .padding(start = AppTheme.padding.s_8),
            key = stringResource(R.string.feature_reactor_volume),
            value = state.value.data.productVolume,
            style = AppTheme.typography.medium,
        )
        KeyValueRow(
            modifier = Modifier
                .fillMaxWidth()
                .padding(start = AppTheme.padding.s_8),
            key = stringResource(R.string.feature_reactor_sell_price),
            value = state.value.data.productSell,
            style = AppTheme.typography.medium,
        )
        KeyValueRow(
            modifier = Modifier
                .fillMaxWidth()
                .padding(start = AppTheme.padding.s_8),
            key = stringResource(R.string.feature_reactor_buy_price),
            value = state.value.data.productBuy,
            style = AppTheme.typography.medium,
        )
        KeyValueRow(
            modifier = Modifier
                .fillMaxWidth()
                .padding(start = AppTheme.padding.s_8, bottom = AppTheme.padding.s_8),
            key = TEXT_EMPTY,
            value = state.value.data.productPriceDif,
            style = AppTheme.typography.medium,
        )

        TextMedium(text = stringResource(R.string.feature_reactor_materials))
        KeyValueRow(
            modifier = Modifier
                .fillMaxWidth()
                .padding(start = AppTheme.padding.s_8),
            key = stringResource(R.string.feature_reactor_volume),
            value = state.value.data.materialVolume,
            style = AppTheme.typography.medium,
        )
        KeyValueRow(
            modifier = Modifier
                .fillMaxWidth()
                .padding(start = AppTheme.padding.s_8),
            key = stringResource(R.string.feature_reactor_sell_price),
            value = state.value.data.materialSell,
            style = AppTheme.typography.medium,
        )
        KeyValueRow(
            modifier = Modifier
                .fillMaxWidth()
                .padding(start = AppTheme.padding.s_8),
            key = stringResource(R.string.feature_reactor_buy_price),
            value = state.value.data.materialBuy,
            style = AppTheme.typography.medium,
        )
        KeyValueRow(
            modifier = Modifier
                .fillMaxWidth()
                .padding(start = AppTheme.padding.s_8),
            key = TEXT_EMPTY,
            value = state.value.data.materialPriceDif,
            style = AppTheme.typography.medium,
        )
    }
}