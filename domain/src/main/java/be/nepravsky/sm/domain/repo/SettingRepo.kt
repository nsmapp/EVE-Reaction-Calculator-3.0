package be.nepravsky.sm.domain.repo

import be.nepravsky.sm.domain.model.settings.PriceSource
import be.nepravsky.sm.domain.model.settings.Settings
import kotlinx.coroutines.flow.Flow

interface SettingRepo {

    fun getPriceSource(): PriceSource

    fun getSettings(): Flow<Settings>

    fun enableOfflineMode()

    fun disableOfflineMode()

    fun enableIgnoreFuelBlockBpc()

    fun disableIgnoreFuelBlockBpc()

    fun updateSearchLanguage(languageId: Long)
}