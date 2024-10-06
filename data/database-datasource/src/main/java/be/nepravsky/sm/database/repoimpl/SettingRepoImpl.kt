package be.nepravsky.sm.database.repoimpl

import app.cash.sqldelight.coroutines.asFlow
import be.nepravsky.sm.database.SettingsTableQueries
import be.nepravsky.sm.domain.model.settings.PriceSource
import be.nepravsky.sm.domain.model.settings.Settings
import be.nepravsky.sm.domain.repo.SettingRepo
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import org.koin.core.annotation.Single

@Single(binds = [SettingRepo::class])
class SettingRepoImpl(
    private val settingsTableQueries: SettingsTableQueries
) : SettingRepo {

    override fun getPriceSource(): PriceSource {
        val source = settingsTableQueries.getPriceSource()
            .executeAsOne()

        return if (source == 1L) PriceSource.OFFLINE else PriceSource.ESI_EVE_TECH
    }

    override fun getSettings(): Flow<Settings> = settingsTableQueries
        .getSettings(mapper = { id, langId, systemId, regionId, isOfflineMode, isIgnoreFuelBlock ->
            Settings(
                id = id,
                langId = langId,
                systemId = systemId,
                regionId = regionId,
                isOfflineMode = isOfflineMode == 1L,
                isIgnoreFuelBlock = isIgnoreFuelBlock == 1L
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

}