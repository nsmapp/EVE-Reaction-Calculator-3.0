package be.nepravsky.sm.domain.model.project

data class ProjectItem(
    val typeId: Long,
    val projectId: Long,
    val run: Long,
    val me: Double,
    val subMe: Double,
)
