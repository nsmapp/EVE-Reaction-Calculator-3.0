package be.belveb.builder.model

data class BpcShortModel(
    val id: Long,
    val groupId: Long,
    val name: String,
    val baseTime: String,
    val runLimit: Long,
)
