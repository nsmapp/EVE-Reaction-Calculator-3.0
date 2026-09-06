package be.nepravsky.sm.evereactioncalculator.news.reactor

import by.niaprauski.navigation.Dest
import kotlinx.serialization.Serializable

@Serializable
sealed class ReactorDest: Dest {

    companion object{
        fun root(reactionId: Long, isSingleReaction: Boolean) = Reactor(reactionId, isSingleReaction)
    }

    @Serializable
    data class Reactor(val reactionId: Long, val isSingleReaction: Boolean): ReactorDest()
}