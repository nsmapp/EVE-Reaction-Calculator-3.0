package be.nepravsky.sm.database.models

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable


@Serializable
data class ReactionItemEntity(
    @SerialName("quantity")
    val quantity: Long,
    @SerialName("typeID")
    val typeId: Long,
)