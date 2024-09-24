package be.nepravsky.sm.domain.repo

import be.nepravsky.sm.domain.model.ReactionGroup
import be.nepravsky.sm.domain.model.query.ActiveGroupQuery
import kotlinx.coroutines.flow.Flow

interface ReactionGroupRepo {

    fun getAll(): List<ReactionGroup>

    fun getActiveGroupId(): Flow<List<Long>>

    fun updateActiveGroups(query: ActiveGroupQuery)
}