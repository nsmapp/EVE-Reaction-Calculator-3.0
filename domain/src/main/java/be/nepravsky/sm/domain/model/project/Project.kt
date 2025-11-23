package be.nepravsky.sm.domain.model.project

import androidx.compose.runtime.Immutable
import kotlinx.collections.immutable.ImmutableList

@Immutable
data class Project(
    val id: Long?,
    val iconId: Long,
    val name: String,
    val description: String,
    val items: ImmutableList<ProjectItem>,
)
