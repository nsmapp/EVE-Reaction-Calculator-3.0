package be.nepravsky.sm.evereactioncalculator.library.model

data class LibraryState(
    val projects: List<ProjectModel>
) {
    companion object {
        val EMPTY = LibraryState(
            projects = listOf()
        )
    }
}
