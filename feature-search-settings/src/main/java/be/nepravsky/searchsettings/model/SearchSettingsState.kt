package be.nepravsky.searchsettings.model

import kotlinx.collections.immutable.*
import be.nepravsky.sm.domain.model.ReactionGroup

data class SearchSettingsState(
    val reactionGroups: ImmutableList<ReactionGroup>
){
    companion object{
        val EMPTY = SearchSettingsState(
            reactionGroups = persistentListOf()
        )
    }
}