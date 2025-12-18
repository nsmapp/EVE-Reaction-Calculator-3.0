package be.nepravsky.sm.evereactioncalculator.library

class LibraryRouterImpl(
    private val onNavigateAddProject: ((Long?) -> Unit),
    private val onNavigateEditProject: ((Long) -> Unit),
    private val onNavigateRunProject: ((Long) -> Unit),
): LibraryRouter {
    override fun addProject(projectId: Long?) {
        onNavigateAddProject(projectId)
    }

    override fun editProject(projectId: Long) {
        onNavigateEditProject(projectId)
    }

    override fun runProject(projectId: Long) {
        onNavigateRunProject(projectId)
    }
}