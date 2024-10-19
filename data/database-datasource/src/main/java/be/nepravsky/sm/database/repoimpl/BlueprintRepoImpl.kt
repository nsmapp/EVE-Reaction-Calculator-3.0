package be.nepravsky.sm.database.repoimpl

import be.nepravsky.sm.database.ReactionTableQueries
import be.nepravsky.sm.database.models.ReactionItemEntity
import be.nepravsky.sm.domain.model.bpc.BpcFull
import be.nepravsky.sm.domain.model.bpc.BpcShort
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
                        groupId = groupId,
                        name = name,
                        baseTime = baseTime,
                        runLimit = runLimit
                    )
                }
            }

    override fun getById(reactionId: Long): BpcFull? = reactionTableQueries
        .getBpcFull(reactionId) { id, groupId, isFormula, baseTime, runLimit, materials, products, name ->
            toBpcFull(id, groupId, isFormula, name, baseTime, runLimit, materials, products)
        }.executeAsOneOrNull()


    override fun getByIds(reactionIds: List<Long>): List<BpcFull> = reactionTableQueries
        .getBpcFullList(reactionIds)
        .executeAsList()
        .map { bpc ->
            toBpcFull(
                id = bpc.id,
                groupId = bpc.groupId,
                isFormula = bpc.isFormula,
                name = bpc.name,
                baseTime = bpc.baseTime,
                runLimit = bpc.runLimit,
                materials = bpc.materials,
                products = bpc.products,
            )
        }

    private fun toBpcFull(
        id: Long,
        groupId: Long,
        isFormula: Boolean,
        name: String,
        baseTime: Long,
        runLimit: Long,
        materials: List<ReactionItemEntity>,
        products: List<ReactionItemEntity>,
    ) = BpcFull(
        id = id,
        groupId = groupId,
        name = name,
        baseTime = baseTime,
        runLimit = runLimit,
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
        },
        isFormula = isFormula
    )

}
