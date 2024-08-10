package be.nepravsky.sm.evereactioncalculator.searchsetttings.contract

import be.nepravsky.sm.domain.model.query.ActiveGroupQuery

interface SearchSettingsContract {

    fun getReactionGroups()

    fun onReactionGroupClick(groupId: Long, isSelected: Boolean)

    fun updateActiveReactions(query: ActiveGroupQuery)
}