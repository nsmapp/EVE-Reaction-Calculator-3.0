package be.nepravsky.sm.evereactioncalculator.model

import be.nepravsky.sm.evereactioncalculator.utils.TEXT_EMPTY

data class SettingsState(
    val id: Long,
    val langId: Long,
    val langName: String,
    val systemId: Long,
    val systemName: String,
    val regionId: Long,
    val isOfflineMode: Boolean,
    val isIgnoreFuelBlock: Boolean,
    val isProgress: Boolean,
    val isShowLanguageDialog: Boolean,
    val languages: List<LanguageModel>
) {
    companion object {
        val EMPTY = SettingsState(
            id = -1,
            langId = -1,
            langName = TEXT_EMPTY,
            systemId = -1,
            systemName = TEXT_EMPTY,
            regionId = -1,
            isOfflineMode = false,
            isIgnoreFuelBlock = false,
            isProgress = true,
            isShowLanguageDialog = false,
            languages = listOf()
        )
    }
}
