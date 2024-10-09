package be.nepravsky.sm.evereactioncalculator

import be.nepravsky.sm.domain.model.settings.Settings

interface SettingsContract {

    /**
     * Handle settings
     *
     * @param settings
     */
    fun handleSettings(settings: Settings)

    /**
     * Set offline mode
     *
     * @param checked
     */
    fun setOfflineMode(checked: Boolean)

    /**
     * Set is ignore fuel block bpc
     *
     * @param checked
     */
    fun setIsIgnoreFuelBlockBpc(checked: Boolean)

    /**
     * Hide dialogs
     *
     */
    fun hideDialogs()

    /**
     * Set search language
     *
     * @param languageId
     */
    fun setSearchLanguage(languageId: Long)

    /**
     * Show search language dialog
     *
     */
    fun showSearchLanguageDialog()
}