package be.nepravsky.sm.evereactioncalculator.model

import be.nepravsky.sm.domain.utils.TEXT_EMPTY


data class ReactorState(
    val data: ComplexReactionModel,
    val showProgress: Boolean,
) {
    companion object {
        val EMPTY = ReactorState(
            data = ComplexReactionModel(
                items = listOf(),

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
            ),
            showProgress = false,
        )
    }
}