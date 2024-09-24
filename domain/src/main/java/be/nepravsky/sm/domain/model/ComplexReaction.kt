package be.nepravsky.sm.domain.model

data class ComplexReaction(
    val reactions: List<CompleteReactionFull>
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
        productQuantity = reactions.sumOf { it.productQuantity }
        productVolume = reactions.sumOf { it.productVolume }
        productSell = reactions.sumOf { it.productSell }
        productBuy = reactions.sumOf { it.productBuy }

        materialQuantity = reactions.sumOf { it.materialQuantity }
        materialVolume = reactions.sumOf { it.materialVolume }
        materialSell = reactions.sumOf { it.materialSell }
        materialBuy = reactions.sumOf { it.materialBuy }
    }
}