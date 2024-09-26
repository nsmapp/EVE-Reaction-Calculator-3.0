package be.nepravsky.sm.domain.repo

import be.nepravsky.sm.domain.model.settings.PriceSource

interface SettingRepo {

    fun getPriceSource(): PriceSource
}