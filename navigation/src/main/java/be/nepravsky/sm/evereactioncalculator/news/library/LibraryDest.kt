package be.nepravsky.sm.evereactioncalculator.news.library

import by.niaprauski.navigation.Dest
import kotlinx.serialization.Serializable

@Serializable
sealed class LibraryDest: Dest {

    companion object{
        fun root() = Library
    }

    @Serializable
    data object Library: LibraryDest()
}