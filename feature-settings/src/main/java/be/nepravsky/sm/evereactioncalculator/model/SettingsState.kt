package be.nepravsky.sm.evereactioncalculator.model

data class SettingsState(
    val id: Long,
    val langId: Long,
    val systemId: Long,
    val regionId: Long,
    val isOfflineMode: Boolean,
    val isIgnoreFuelBlock: Boolean,
    val isProgress: Boolean,
) {
    companion object {
        val EMPTY = SettingsState(
            id = -1,
            langId = -1,
            systemId = -1,
            regionId = -1,
            isOfflineMode = false,
            isIgnoreFuelBlock = false,
            isProgress = true,
        )
    }
}
