package be.nepravsky.sm.evereactioncalculator.news.settings

import by.niaprauski.navigation.Dest
import kotlinx.serialization.Serializable

@Serializable
sealed class SettingsDest: Dest {

    companion object{
        fun root() = Settings
    }

    @Serializable
    data object Settings: SettingsDest()
}