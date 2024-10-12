package be.nepravsky.sm.domain.model

data class ReactionItemFull(

    val id: Long,
    val groupId: Long,
    val name: String,
    val quantity: Long,
    val volume: Double,
    val sell: Double,
    val buy: Double,
    val updateTime: Long,
)