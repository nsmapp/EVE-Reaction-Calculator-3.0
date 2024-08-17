package be.nepravsky.sm.database.network.model.response

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class PriceResponse(
    @SerialName("is_buy_order")
    val isBuyOrder: Boolean,
    @SerialName("price")
    val price: Double,
    @SerialName("type_id")
    val typeId: Int
)