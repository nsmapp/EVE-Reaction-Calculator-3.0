package be.nepravsky.searchsettings.model

import be.nepravsky.sm.domain.model.ReactionGroup

data class SearchSettingsState(
    val reactionGroups: List<ReactionGroup>
){
    companion object{
        val EMPTY = SearchSettingsState(
            reactionGroups = emptyList()
        )
    }
}