package be.nepravsky.sm.evereactioncalculator.searchsetttings.model

import be.nepravsky.sm.domain.model.ReactionGroup

data class SearchSettingsState(
    val reactionGroups: List<ReactionGroup>
){
    companion object{
        val INIT = SearchSettingsState(
            reactionGroups = emptyList()
        )
    }
}