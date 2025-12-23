package be.nepravsky.sm.evereactioncalculator.library.model

import androidx.compose.runtime.Stable
import kotlinx.collections.immutable.ImmutableList
import kotlinx.collections.immutable.persistentListOf

@Stable
data class LibraryState(
    val projects: ImmutableList<ProjectModel>
) {
    companion object {
        val EMPTY = LibraryState(
            projects = persistentListOf()
        )
    }
}
