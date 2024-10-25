package be.nepravsky.sm.evereactioncalculator.model

data class ReactionItemModel(
    val id: Long,
    val groupId: Long,
    val name: String,
    val quantity: String,
    val volume: String,
    val sell: String,
    val buy: String,
    val isProduct: Boolean,
    val updateTime: String,
    val hasZeroPrice: Boolean
)