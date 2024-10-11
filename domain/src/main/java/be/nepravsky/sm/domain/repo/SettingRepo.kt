package be.nepravsky.sm.domain.repo

import be.nepravsky.sm.domain.model.query.PriceLocationQuery
import be.nepravsky.sm.domain.model.settings.PriceSource
import be.nepravsky.sm.domain.model.settings.Settings
import kotlinx.coroutines.flow.Flow

interface SettingRepo {

    fun getPriceSource(): PriceSource

    fun getDefaultRegionId(): Long

    fun getDefaultSolarSystemId(): Long

    fun getSettingsFlow(): Flow<Settings>

    fun enableOfflineMode()

    fun disableOfflineMode()

    fun enableIgnoreFuelBlockBpc()

    fun disableIgnoreFuelBlockBpc()

    fun updateSearchLanguage(languageId: Long)

    fun updatePriceLocationSettings(query: PriceLocationQuery)
}