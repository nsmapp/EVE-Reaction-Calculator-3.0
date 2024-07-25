package be.nepravsky.sm.database.repoimpl

import be.nepravsky.sm.database.ReactionTableQueries
import be.nepravsky.sm.domain.model.Blueprint
import be.nepravsky.sm.domain.query.ReactionsQuery
import be.nepravsky.sm.domain.repo.BlueprintRepo
import org.koin.core.annotation.Single

@Single(binds = [BlueprintRepo::class])
class BlueprintRepoImpl(
    private val reactionTableQueries: ReactionTableQueries,
) : BlueprintRepo {

    override fun get(query: ReactionsQuery): List<Blueprint> =
        reactionTableQueries
            .getAll()
            .executeAsList()
            .map { reaction -> reaction.toBlueprint() }

}
