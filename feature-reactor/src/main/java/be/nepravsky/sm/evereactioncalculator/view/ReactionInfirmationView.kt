package be.nepravsky.sm.evereactioncalculator.view

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import be.nepravsky.sm.domain.utils.TEXT_EMPTY
import be.nepravsky.sm.evereactioncalculator.model.ReactionTab
import be.nepravsky.sm.evereactioncalculator.model.ReactorState
import be.nepravsky.sm.evereactioncalculator.uikit.R
import be.nepravsky.sm.uikit.theme.AppTheme
import be.nepravsky.sm.uikit.view.row.KeyValueRow
import be.nepravsky.sm.uikit.view.text.TextMedium

@Composable
fun ReactionInformationView(
    state: ReactorState,
    selectedTabIndex: Int,
) {

    val data = state.data
    val isBaseTab = ReactionTab.BASE_TYPE.ordinal == selectedTabIndex


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
            value = if (isBaseTab) data.productVolume else data.fullProductVolume,
            style = AppTheme.typography.medium,
        )
        KeyValueRow(
            modifier = Modifier
                .fillMaxWidth()
                .padding(start = AppTheme.padding.s_8),
            key = stringResource(R.string.feature_reactor_sell_price),
            value = if (isBaseTab) data.productSell else data.fullProductSell,
            style = AppTheme.typography.medium,
        )
        KeyValueRow(
            modifier = Modifier
                .fillMaxWidth()
                .padding(start = AppTheme.padding.s_8),
            key = stringResource(R.string.feature_reactor_buy_price),
            value = if (isBaseTab) data.productBuy else data.fullProductBuy,
            style = AppTheme.typography.medium,
        )
        KeyValueRow(
            modifier = Modifier
                .fillMaxWidth()
                .padding(start = AppTheme.padding.s_8, bottom = AppTheme.padding.s_8),
            key = TEXT_EMPTY,
            value = if (isBaseTab) data.productPriceDif else data.fullProductPriceDif,
            style = AppTheme.typography.medium,
        )

        TextMedium(text = stringResource(R.string.feature_reactor_materials))
        KeyValueRow(
            modifier = Modifier
                .fillMaxWidth()
                .padding(start = AppTheme.padding.s_8),
            key = stringResource(R.string.feature_reactor_volume),
            value = if (isBaseTab) data.materialVolume else data.fullMaterialVolume,
            style = AppTheme.typography.medium,
        )
        KeyValueRow(
            modifier = Modifier
                .fillMaxWidth()
                .padding(start = AppTheme.padding.s_8),
            key = stringResource(R.string.feature_reactor_sell_price),
            value = if (isBaseTab) data.materialSell else data.fullMaterialSell,
            style = AppTheme.typography.medium,
        )
        KeyValueRow(
            modifier = Modifier
                .fillMaxWidth()
                .padding(start = AppTheme.padding.s_8),
            key = stringResource(R.string.feature_reactor_buy_price),
            value = if (isBaseTab) data.materialBuy else data.fullMaterialBuy,
            style = AppTheme.typography.medium,
        )
        KeyValueRow(
            modifier = Modifier
                .fillMaxWidth()
                .padding(start = AppTheme.padding.s_8),
            key = TEXT_EMPTY,
            value = if (isBaseTab) data.materialPriceDif else data.fullMaterialPriceDif,
            style = AppTheme.typography.medium,
        )
    }
}