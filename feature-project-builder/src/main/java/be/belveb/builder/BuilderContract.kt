package be.belveb.builder

import be.belveb.builder.model.BpcShortModel

interface BuilderContract {

    /**
     * Init data
     *
     */
    fun initData()

    /**
     * Set project name
     *
     * @param name - project name
     */
    fun setProjectName(name: String)

    /**
     * Save project
     *
     */
    fun saveProject()

    /**
     * Show type bottom sheet
     *
     */
    fun showTypeBottomSheet()

    /**
     * Hide type bottom sheet
     *
     */
    fun hideTypeBottomSheet()

    /**
     * Add project item
     *
     * @param type
     */
    fun addProjectItem(type: BpcShortModel)

    /**
     * Get bpc list
     *
     * @param text
     */
    fun getBpcList(text: String)

    /**
     * Get load project
     *
     * @param projectId
     */
    fun loadProject(projectId: Long)

}