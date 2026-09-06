package be.nepravsky.sm.evereactioncalculator.news.reactions

import by.niaprauski.navigation.Dest
import kotlinx.serialization.Serializable

@Serializable
sealed class ReactionsDest: Dest {

    companion object{
        fun root() = Reactions
    }

    @Serializable
    data object Reactions: ReactionsDest()
}