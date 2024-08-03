package be.nepravsky.sm.domain.repo

import be.nepravsky.sm.domain.model.ReactionGroup
import kotlinx.coroutines.flow.Flow

interface ReactionGroupRepository {

    fun getAll(): List<ReactionGroup>

    fun getActiveGroupId(): Flow<List<Long>>
}