package be.nepravsky.sm.evereactioncalculator.news.about

import androidx.navigation3.runtime.NavKey
import kotlinx.serialization.Serializable

@Serializable
sealed class AboutDest: NavKey {

    companion object{
        fun root() = About
    }

    @Serializable
    data object About: AboutDest()

}