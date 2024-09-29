package be.nepravsky.sm.domain.model

data class TypePrice(
    val id: Long,
    val systemId: Long,
    val regionId: Long,
    val sell: Double,
    val buy: Double,
    val updateTime: Long,
)