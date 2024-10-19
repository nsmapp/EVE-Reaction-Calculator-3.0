package be.nepravsky.sm.domain.model.project

data class Project(
    val id: Long?,
    val iconId: Long,
    val name: String,
    val description: String,
    val items: List<ProjectItem>,
)
