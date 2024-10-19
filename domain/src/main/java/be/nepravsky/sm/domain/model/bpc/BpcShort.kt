package be.nepravsky.sm.domain.model.bpc

data class BpcShort(
    val id: Long,
    val groupId: Long,
    val name: String,
    val baseTime: Long,
    val runLimit: Long,
)
