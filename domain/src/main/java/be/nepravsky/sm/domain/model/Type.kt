package be.nepravsky.sm.domain.model

data class Type(
    val id: Long,
    val groupId: Long,
    val basePrice: Double,
    val volume: Double,
    val name: String,
)