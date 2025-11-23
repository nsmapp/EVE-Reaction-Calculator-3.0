package be.nepravsky.sm.evereactioncalculator.library.model

import kotlinx.collections.immutable.ImmutableList
import kotlinx.collections.immutable.persistentListOf

data class LibraryState(
    val projects: ImmutableList<ProjectModel>
) {
    companion object {
        val EMPTY = LibraryState(
            projects = persistentListOf()
        )
    }
}
