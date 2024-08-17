package be.nepravsky.sm.database.repoimpl

import be.nepravsky.sm.database.ReactionTableQueries
import be.nepravsky.sm.domain.model.BpcFull
import be.nepravsky.sm.domain.model.BpcShort
import be.nepravsky.sm.domain.model.ReactionItem
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
                with(reaction) {
                    BpcShort(
                        id = id,
                        groupId = group_id,
                        name = name,
                        baseTime = base_time,
                        runLimit = run_limit
                    )
                }
            }

    override fun getById(reactionId: Long): BpcFull? {
        val entity = reactionTableQueries
            .getBpcFull(reactionId)
            .executeAsOneOrNull()

        if (entity == null) return entity

        return with(entity) {
            BpcFull(
                id = id,
                groupId = group_id,
                name = name,
                baseTime = base_time,
                runLimit = run_limit,
                materials = materials.map { item ->
                    ReactionItem(
                        quantity = item.quantity,
                        item.typeId
                    )
                },
                products = products.map { item ->
                    ReactionItem(
                        quantity = item.quantity,
                        item.typeId
                    )
                }

            )
        }
    }
}
