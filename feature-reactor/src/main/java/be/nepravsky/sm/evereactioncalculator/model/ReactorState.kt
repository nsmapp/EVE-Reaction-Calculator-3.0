package be.nepravsky.sm.evereactioncalculator.model

import androidx.compose.runtime.Stable
import be.nepravsky.sm.domain.utils.TEXT_EMPTY
import kotlinx.collections.immutable.persistentListOf

@Stable
data class ReactorState(
    val data: ComplexReactionModel,
    val isShowProgress: Boolean,
    val isSingleReaction: Boolean,
    val isShowShareDialog: Boolean,
    val isShowReactionInformation: Boolean,
    val isOfflineMode: Boolean,
    val run: String,
    val me: String,
    val subMe: String,
    val isMeEnabled: Boolean = true,
) {
    companion object {
        val EMPTY = ReactorState(
            data = ComplexReactionModel(
                baseItems = persistentListOf(),

                productQuantity = TEXT_EMPTY,
                productVolume = TEXT_EMPTY,
                productSell = TEXT_EMPTY,
                productBuy = TEXT_EMPTY,
                productPriceDif = TEXT_EMPTY,

                materialQuantity = TEXT_EMPTY,
                materialVolume = TEXT_EMPTY,
                materialSell = TEXT_EMPTY,
                materialBuy = TEXT_EMPTY,
                materialPriceDif = TEXT_EMPTY,

                items = persistentListOf(),
                fullProductQuantity = TEXT_EMPTY,
                fullProductVolume = TEXT_EMPTY,
                fullProductSell = TEXT_EMPTY,
                fullProductBuy = TEXT_EMPTY,
                fullProductPriceDif = TEXT_EMPTY,
                fullMaterialQuantity = TEXT_EMPTY,
                fullMaterialVolume = TEXT_EMPTY,
                fullMaterialSell = TEXT_EMPTY,
                fullMaterialBuy = TEXT_EMPTY,
                fullMaterialPriceDif = TEXT_EMPTY,

                hasZeroPrice = false,
            ),
            isShowProgress = false,
            isSingleReaction = false,
            isShowShareDialog = false,
            isShowReactionInformation = false,
            isOfflineMode = false,
            run = "1",
            me = "0",
            subMe = "0"
        )
    }
}