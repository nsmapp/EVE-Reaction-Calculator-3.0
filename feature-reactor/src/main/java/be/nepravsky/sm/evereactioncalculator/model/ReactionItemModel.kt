package be.nepravsky.sm.evereactioncalculator.model

class ReactionItemModel(
    val id: Long,
    val name: String,
    val quantity: String,
    val volume: String,
    val sell: String,
    val buy: String,
    val isProduct: Boolean,
)