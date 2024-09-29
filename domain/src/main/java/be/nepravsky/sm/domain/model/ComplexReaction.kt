package be.nepravsky.sm.domain.model

data class ComplexReaction(
    val baseReactions: List<CompleteReactionFull>,
    val reactions: List<CompleteReactionFull>,
){

    val productQuantity: Long
    val productVolume: Double
    val productSell: Double
    val productBuy: Double

    val materialQuantity: Long
    val materialVolume: Double
    val materialSell: Double
    val materialBuy: Double

    val fullProductQuantity: Long
    val fullProductVolume: Double
    val fullProductSell: Double
    val fullProductBuy: Double

    val fullMaterialQuantity: Long
    val fullMaterialVolume: Double
    val fullMaterialSell: Double
    val fullMaterialBuy: Double

    init {
        productQuantity = baseReactions.sumOf { it.productQuantity }
        productVolume = baseReactions.sumOf { it.productVolume }
        productSell = baseReactions.sumOf { it.productSell }
        productBuy = baseReactions.sumOf { it.productBuy }

        materialQuantity = baseReactions.sumOf { it.materialQuantity }
        materialVolume = baseReactions.sumOf { it.materialVolume }
        materialSell = baseReactions.sumOf { it.materialSell }
        materialBuy = baseReactions.sumOf { it.materialBuy }

        fullProductQuantity = reactions.sumOf { it.productQuantity }
        fullProductVolume = reactions.sumOf { it.productVolume }
        fullProductSell = reactions.sumOf { it.productSell }
        fullProductBuy = reactions.sumOf { it.productBuy }

        fullMaterialQuantity = reactions.sumOf { it.materialQuantity }
        fullMaterialVolume = reactions.sumOf { it.materialVolume }
        fullMaterialSell = reactions.sumOf { it.materialSell }
        fullMaterialBuy = reactions.sumOf { it.materialBuy }
    }
}