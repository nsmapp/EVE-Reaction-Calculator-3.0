package be.nepravsky.sm.database.repoimpl

import be.nepravsky.sm.database.ReactionTableQueries
import be.nepravsky.sm.domain.model.BpcShort
import be.nepravsky.sm.domain.model.query.ReactionsQuery
import be.nepravsky.sm.domain.repo.BlueprintRepo
import org.koin.core.annotation.Single

@Single(binds = [BlueprintRepo::class])
class BlueprintRepoImpl(
    private val reactionTableQueries: ReactionTableQueries,
) : BlueprintRepo {

    override fun get(query: ReactionsQuery): List<BpcShort> =
        reactionTableQueries
            .getBpcShort(query.name, query.groupIds)
            .executeAsList()
            .map { reaction ->
                with(reaction){
                    BpcShort(
                        id = id,
                        groupId = group_id,
                        name = name,
                        baseTime = base_time,
                        runLimit = run_limit
                    )
                }
            }

}
