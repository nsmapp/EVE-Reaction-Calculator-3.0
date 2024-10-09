package be.nepravsky.sm.database.repoimpl

import app.cash.sqldelight.coroutines.asFlow
import be.nepravsky.sm.database.LanguageTableQueries
import be.nepravsky.sm.database.SettingsTableQueries
import be.nepravsky.sm.domain.model.settings.Language
import be.nepravsky.sm.domain.model.settings.PriceSource
import be.nepravsky.sm.domain.model.settings.Settings
import be.nepravsky.sm.domain.repo.SettingRepo
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import org.koin.core.annotation.Single

@Single(binds = [SettingRepo::class])
class SettingRepoImpl(
    private val settingsTableQueries: SettingsTableQueries,
    private val languageTableQueries: LanguageTableQueries,
) : SettingRepo {

    override fun getPriceSource(): PriceSource {
        val source = settingsTableQueries.getPriceSource()
            .executeAsOne()

        return if (source == 1L) PriceSource.OFFLINE else PriceSource.ESI_EVE_TECH
    }

    override fun getSettings(): Flow<Settings> = settingsTableQueries
        .getSettings(mapper = { id, langId, systemId, regionId, isOfflineMode, isIgnoreFuelBlock, langName, systemName ->
            Settings(
                id = id,
                langId = langId,
                langName = langName,
                systemId = systemId,
                systemName = systemName,
                regionId = regionId,
                isOfflineMode = isOfflineMode == 1L,
                isIgnoreFuelBlock = isIgnoreFuelBlock == 1L,
                languages = getLanguage()

            )
        })
        .asFlow()
        .map { it.executeAsOne() }

    override fun enableOfflineMode() {
        settingsTableQueries.enableOfflineMode()
    }

    override fun disableOfflineMode() {
        settingsTableQueries.disableOfflineMode()
    }

    override fun enableIgnoreFuelBlockBpc() {
        settingsTableQueries.enableIgnoreFuelBlockBpc()
    }

    override fun disableIgnoreFuelBlockBpc() {
        settingsTableQueries.disableIgnoreFuelBlockBpc()
    }

    override fun updateSearchLanguage(languageId: Long) {
        settingsTableQueries.updateSearchLanguage(languageId)
    }

    private fun getLanguage(): List<Language> =
        languageTableQueries.getAll(mapper = { id: Long, name: String -> Language(id, name) })
            .executeAsList()

}