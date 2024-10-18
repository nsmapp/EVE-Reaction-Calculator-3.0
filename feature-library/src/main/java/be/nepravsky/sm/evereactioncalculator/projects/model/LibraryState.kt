package be.nepravsky.sm.evereactioncalculator.projects.model

data class LibraryState(
    val projects: List<ProjectModel>
) {
    companion object {
        val EMPTY = LibraryState(
            projects = listOf()
        )
    }
}
