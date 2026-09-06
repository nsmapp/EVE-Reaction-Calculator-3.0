package be.nepravsky.sm.evereactioncalculator.news.searchsettings

import by.niaprauski.navigation.Dest
import kotlinx.serialization.Serializable

@Serializable
sealed class SearchSettingsDest: Dest {

    companion object{
        fun root() = SearchSettings
    }

    @Serializable
    data object SearchSettings: SearchSettingsDest()
}