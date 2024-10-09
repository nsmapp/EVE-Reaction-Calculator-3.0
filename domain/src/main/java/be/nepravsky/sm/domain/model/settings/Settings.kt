package be.nepravsky.sm.domain.model.settings

data class Settings(
    val id: Long,
    val langId: Long,
    val langName: String,
    val systemId: Long,
    val systemName: String,
    val regionId: Long,
    val isOfflineMode: Boolean,
    val isIgnoreFuelBlock: Boolean,
    val languages: List<Language>
)
