package be.nepravsky.sm.evereactioncalculator.projects

interface LibraryContract {

    /**
     * Load projects
     *
     */
    fun loadProjects()

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

    /**
     * Delete project
     *
     * @param projectId
     */
    fun deleteProject(projectId: Long)

}