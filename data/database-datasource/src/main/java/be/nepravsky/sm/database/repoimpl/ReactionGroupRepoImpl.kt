package be.nepravsky.sm.database.repoimpl

import app.cash.sqldelight.coroutines.asFlow
import app.cash.sqldelight.coroutines.mapToList
import be.nepravsky.sm.database.TypeGroupTableQueries
import be.nepravsky.sm.domain.model.ReactionGroup
import be.nepravsky.sm.domain.model.query.ActiveGroupQuery
import be.nepravsky.sm.domain.repo.ReactionGroupRepo
import be.nepravsky.sm.domain.utils.DispatcherProvider
import kotlinx.coroutines.flow.Flow
import org.koin.core.annotation.Single


@Single(binds = [ReactionGroupRepo::class])
class ReactionGroupRepoImpl(
    private val groupsSource: TypeGroupTableQueries,
    private val dispatcherProvider: DispatcherProvider,
) : ReactionGroupRepo {


    override fun getAll(): List<ReactionGroup> = groupsSource.getAll()
        .executeAsList()
        .map { group ->
            with(group) {
                ReactionGroup(
                    id = id,
                    isFormula = isFormula,
                    isSelected = isSelected,
                    category = category,
                    name = name,
                    iconId = iconId,
                )
            }

        }


    override fun getActiveGroupId(): Flow<List<Long>> = groupsSource.getActiveGroupIds()
        .asFlow()
        .mapToList(dispatcherProvider.io)


    override fun updateActiveGroups(query: ActiveGroupQuery) {
        groupsSource.updateActiveGroups(query.isSelected, query.id)
    }

    override fun clearActiveGroups() {
        groupsSource.clearActiveGroups()
    }


}