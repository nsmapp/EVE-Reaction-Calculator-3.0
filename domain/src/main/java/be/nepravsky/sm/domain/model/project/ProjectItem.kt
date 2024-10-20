package be.nepravsky.sm.domain.model.project

import be.nepravsky.sm.domain.utils.TEXT_EMPTY

data class ProjectItem(
    val reactionId: Long,
    val projectId: Long?,
    val run: Long,
    val me: Double,
    val subMe: Double,
    val name: String = TEXT_EMPTY,
)
