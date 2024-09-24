package be.nepravsky.sm.evereactioncalculator.model

data class ComplexReactionModel(
    val items: List<ReactionItemModel>,

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
)