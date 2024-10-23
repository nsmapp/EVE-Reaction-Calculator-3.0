package be.nepravsky.sm.evereactioncalculator.model

data class ComplexReactionModel(
    val baseItems: List<ReactionItemModel>,

    val productQuantity: String,
    val productVolume: String,
    val productSell: String,
    val productBuy: String,
    val productPriceDif: String,

    val materialQuantity: String,
    val materialVolume: String,
    val materialSell: String,
    val materialBuy: String,
    val materialPriceDif: String,

    val items: List<ReactionItemModel>,

    val fullProductQuantity: String,
    val fullProductVolume: String,
    val fullProductSell: String,
    val fullProductBuy: String,
    val fullProductPriceDif: String,

    val fullMaterialQuantity: String,
    val fullMaterialVolume: String,
    val fullMaterialSell: String,
    val fullMaterialBuy: String,
    val fullMaterialPriceDif: String,

    val hasZeroPrice: Boolean,
)