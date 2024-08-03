package be.nepravsky.sm.domain.model

data class BpcShort(
    val id: Long,
    val groupId: Long,
    val name: String,
    val baseTime: Long,
    val runLimit: Long,
)
