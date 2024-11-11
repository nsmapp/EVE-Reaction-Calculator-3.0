package be.nepravsky.sm.evereactioncalculator.library

interface LibraryRouter {


    /**
     * Add project
     *
     * @param projectId
     */
    fun addProject(projectId: Long?)

    /**
     * Open project
     *
     * @param projectId
     */
    fun editProject(projectId: Long)

    /**
     * Run project
     *
     * @param projectId
     */
    fun runProject(projectId: Long)
}