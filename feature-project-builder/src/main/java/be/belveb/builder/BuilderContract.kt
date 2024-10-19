package be.belveb.builder

interface BuilderContract {

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
}