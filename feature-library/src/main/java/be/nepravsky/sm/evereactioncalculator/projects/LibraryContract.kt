package be.nepravsky.sm.evereactioncalculator.projects

interface LibraryContract {

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
     * Delete project
     *
     * @param projectId
     */
    fun deleteProject(projectId: Long)


    /**
     * Get all without items
     *
     * @return
     */
    fun getAllWithoutItems()

}