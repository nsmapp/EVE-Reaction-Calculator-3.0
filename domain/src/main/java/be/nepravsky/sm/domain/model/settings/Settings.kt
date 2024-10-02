package be.nepravsky.sm.domain.model.settings

data class Settings(
    val id: Long,
    val langId: Long,
    val systemId: Long,
    val regionId: Long,
    val isOfflineMode: Boolean,
    val isIgnoreFuelBlock: Boolean,
)
