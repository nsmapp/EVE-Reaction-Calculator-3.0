package be.nepravsky.sm.database.repoimpl

import be.nepravsky.sm.database.SettingsTableQueries
import be.nepravsky.sm.domain.model.settings.PriceSource
import be.nepravsky.sm.domain.repo.SettingRepo
import org.koin.core.annotation.Single

@Single(binds = [SettingRepo::class])
class SettingRepoImpl(
    private val settingsTableQueries: SettingsTableQueries
): SettingRepo {

    override fun getPriceSource(): PriceSource {
        val source = settingsTableQueries.getPriceSource()
            .executeAsOne()

        return if (source == 1L) PriceSource.ESI_EVE_TECH else PriceSource.OFFLINE
    }
}