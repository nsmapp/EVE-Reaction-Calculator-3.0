package be.nepravsky.sm.domain.model

data class CompleteReactionFull(
    val products: List<ReactionItemFull>,
    val materials: List<ReactionItemFull>,
){

    val productQuantity: Long
    val productVolume: Double
    val productSell: Double
    val productBuy: Double

    val materialQuantity: Long
    val materialVolume: Double
    val materialSell: Double
    val materialBuy: Double

    init {
        productQuantity = products.sumOf { it.quantity }
        productVolume = products.sumOf { it.volume }
        productSell = products.sumOf { it.sell }
        productBuy = products.sumOf { it.buy }

        materialQuantity = materials.sumOf { it.quantity }
        materialVolume = materials.sumOf { it.volume }
        materialSell = materials.sumOf { it.sell }
        materialBuy = materials.sumOf { it.buy }
    }
}