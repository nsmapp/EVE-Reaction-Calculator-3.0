package be.belveb.builder.model

data class ProjectItemModel(
    val id: Long,
    val name: String,
    val runCount: Long,
    val me: Double,
    val subMe: Double,
)
