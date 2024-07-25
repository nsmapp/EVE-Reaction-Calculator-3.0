package be.nepravsky.sm.domain.model

data class Blueprint(
    val id: Long,
    val groupId: Long,
    val de: String,
    val en: String,
    val fr: String,
    val ja: String,
    val ru: String,
    val zh: String,
    val baseTime: Long,
    val runLimit: Long,
    val materials: String,
    val products: String,
)
