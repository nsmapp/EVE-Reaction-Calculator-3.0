package be.nepravsky.sm.evereactioncalculator.projects

interface LibraryRouter{


    /**
     * Add project
     *
     */
    fun addProject()

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