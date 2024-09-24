package be.nepravsky.sm.domain.usecase.reactor

import be.nepravsky.sm.domain.model.BpcFull
import be.nepravsky.sm.domain.model.CompleteReactionFull
import be.nepravsky.sm.domain.model.CompleteReactionShort
import be.nepravsky.sm.domain.model.ComplexReaction
import be.nepravsky.sm.domain.model.ReactionItem
import be.nepravsky.sm.domain.model.ReactionItemFull
import be.nepravsky.sm.domain.model.Type
import be.nepravsky.sm.domain.model.TypePrice
import be.nepravsky.sm.domain.model.query.ReactionQuery
import be.nepravsky.sm.domain.repo.BlueprintRepo
import be.nepravsky.sm.domain.repo.TypePriceRepo
import be.nepravsky.sm.domain.repo.TypeRepo
import be.nepravsky.sm.domain.utils.DispatcherProvider
import be.nepravsky.sm.domain.utils.TEXT_EMPTY
import kotlinx.coroutines.withContext
import org.koin.core.annotation.Factory
import kotlin.math.ceil


@Factory
class MakeReactionUseCase(
    private val blueprintRepo: BlueprintRepo,
    private val priceRepo: TypePriceRepo,
    private val typeRepo: TypeRepo,
    private val dispatcherProvider: DispatcherProvider,
) {


    suspend fun invoke(query: List<ReactionQuery>): Result<ComplexReaction> =
        withContext(dispatcherProvider.io) {
            runCatching {
                val shortReactions = getCompleteReactionShotList(query)
                val typeIds: List<Long> = shortReactions.map { it.typeIdSet }.flatten().distinct()
                val prices: Map<Long, TypePrice> = priceRepo.getByIds(typeIds).associateBy { it.id }
                val types: Map<Long, Type> = typeRepo.getByIds(typeIds).associateBy { it.id }

                val completeReactionFullList = shortReactions.map { short ->
                    CompleteReactionFull(
                        products = short.products
                            .map { item -> mapReactionItemToFull(item, types, prices) },
                        materials = short.materials
                            .map { item -> mapReactionItemToFull(item, types, prices) }
                    )
                }
                ComplexReaction(completeReactionFullList)
            }
        }

    private fun mapReactionItemToFull(
        item: ReactionItem,
        types: Map<Long, Type>,
        prices: Map<Long, TypePrice>
    ) = ReactionItemFull(
        id = item.typeId,
        name = types[item.typeId]?.name ?: TEXT_EMPTY,
        quantity = item.quantity,
        volume = item.quantity * (types[item.typeId]?.volume ?: 0.0),
        sell = item.quantity * (prices[item.typeId]?.sell ?: 0.0),
        buy = item.quantity * (prices[item.typeId]?.buy ?: 0.0),
    )


    private fun getCompleteReactionShotList(query: List<ReactionQuery>): List<CompleteReactionShort> {

        val completeReactionsShort = mutableListOf<CompleteReactionShort>()

        query.forEach { build ->
            blueprintRepo.getById(build.bpcId)?.let { bpc ->

                val typeSet = mutableSetOf<Long>()
                val products = calcReactionItemForRun(bpc.products, build.run)
                val materials = mutableListOf<ReactionItem>()

                var subMaterials = calcReactionItemForRun(bpc.materials, build.run)

                do {
                    val subProducts = mutableListOf<ReactionItem>()
                    val baseMaterials = mutableListOf<ReactionItem>()

                    subMaterials.forEach { item ->
                        val subBpc = blueprintRepo.getById(item.typeId)
                        if (subBpc == null) baseMaterials.add(item)
                        else {
                            val run = calculateRun(item, subBpc)
                            subProducts.addAll(calcReactionItemForRun(subBpc.materials, run))
                        }
                    }

                    subMaterials = subProducts
                    materials.addAll(baseMaterials)

                } while (subProducts.isNotEmpty())

                products.forEach { product -> typeSet.add(product.typeId) }
                materials.forEach { material -> typeSet.add(material.typeId) }

                val shorReaction = CompleteReactionShort(products, materials, typeSet)
                completeReactionsShort.add(shorReaction)
            }
        }

        return completeReactionsShort.toList()
    }

    private fun calculateRun(item: ReactionItem, subBpc: BpcFull): Long =
        ceil(item.quantity.toDouble() / subBpc.products.first { it.typeId == item.typeId }.quantity).toLong()

    private fun calcReactionItemForRun(
        items: List<ReactionItem>,
        run: Long,
    ): List<ReactionItem> = items.map { item -> item.copy(quantity = item.quantity * run) }

}