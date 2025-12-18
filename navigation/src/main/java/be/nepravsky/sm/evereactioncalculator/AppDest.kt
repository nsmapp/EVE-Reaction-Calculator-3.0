package be.nepravsky.sm.evereactioncalculator

import androidx.navigation3.runtime.NavKey
import kotlinx.serialization.Serializable

sealed class AppDest : NavKey{

    @Serializable
    object Root : AppDest()

    @Serializable
    object SearchSettings : AppDest()

    @Serializable
    data class Reactor(
        val id: Long,
        val isSingleReaction: Boolean,
    ) : AppDest()

    @Serializable
    data class ProjectBuilder(
        val projectId: Long?,
    ) : AppDest()

    @Serializable
    data object About : AppDest()

}
