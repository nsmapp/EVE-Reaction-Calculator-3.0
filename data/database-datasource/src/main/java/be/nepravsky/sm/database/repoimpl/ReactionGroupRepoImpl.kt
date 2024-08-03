package be.nepravsky.sm.database.repoimpl

import app.cash.sqldelight.coroutines.asFlow
import app.cash.sqldelight.coroutines.mapToList
import be.nepravsky.sm.database.TypeGroupTableQueries
import be.nepravsky.sm.domain.model.ReactionGroup
import be.nepravsky.sm.domain.repo.ReactionGroupRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import org.koin.core.annotation.Single


@Single(binds = [ReactionGroupRepository::class])
class ReactionGroupRepoImpl(
    private val groupsSource: TypeGroupTableQueries,
): ReactionGroupRepository {


    override fun getAll(): List<ReactionGroup> {
        TODO("Not yet implemented")
    }

    override fun getActiveGroupId(): Flow<List<Long>> = groupsSource.getActiveGroupIds()
        .asFlow()
        .mapToList(Dispatchers.IO)
}