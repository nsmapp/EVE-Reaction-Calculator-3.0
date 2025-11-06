package be.nepravsky.searchsettings.contract

import be.nepravsky.sm.domain.model.query.ActiveGroupQuery

interface SearchSettingsContract {

    fun getReactionGroups()

    fun onReactionGroupClick(groupId: Long, isSelected: Boolean)

    fun updateActiveReactions(query: ActiveGroupQuery)

    fun cleanFilter()
}