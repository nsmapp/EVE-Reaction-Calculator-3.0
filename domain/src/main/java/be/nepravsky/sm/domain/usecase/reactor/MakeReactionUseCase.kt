package be.nepravsky.sm.domain.usecase.reactor

import be.nepravsky.sm.domain.model.bpc.BpcFull
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

                //calc reaction with base materials
                val baseReactionShort: List<CompleteReactionShort> = getBaseReactionShortList(query)
                val baseReaction: List<CompleteReactionFull> = getBaseReactionFull(baseReactionShort)

                //calc simple reaction
                val reactionShort: List<CompleteReactionShort> = getReactionShortList(query)
                val reaction: List<CompleteReactionFull> = getReactionFull(reactionShort)

                ComplexReaction(
                    baseReactions = baseReaction,
                    reactions = reaction,
                    isSingleMeReaction = query.size == 1 && baseReactionShort.all { !it.isFormula }
                )
            }
        }

    private fun getBaseReactionFull(baseReactionShort: List<CompleteReactionShort>): List<CompleteReactionFull> {
        val baseReaction = getReactionFull(baseReactionShort)
        return baseReaction
    }

    private fun getReactionFull(reactionShort: List<CompleteReactionShort>): List<CompleteReactionFull> {
        val typeIds: List<Long> = reactionShort.map { it.typeIdSet }.flatten().distinct()
        val prices: Map<Long, TypePrice> = priceRepo.getByIds(typeIds).associateBy { it.id }
        val types: Map<Long, Type> = typeRepo.getByIds(typeIds).associateBy { it.id }
        val reaction = reactionShort.map { short ->
            CompleteReactionFull(
                products = short.products
                    .map { item -> mapReactionItemToFull(item, types, prices) },
                materials = short.materials
                    .map { item -> mapReactionItemToFull(item, types, prices) }
            )
        }
        return reaction
    }

    private fun mapReactionItemToFull(
        item: ReactionItem,
        types: Map<Long, Type>,
        prices: Map<Long, TypePrice>
    ) = ReactionItemFull(
        id = item.typeId,
        groupId = types[item.typeId]?.groupId ?: -1L,
        name = types[item.typeId]?.name ?: TEXT_EMPTY,
        quantity = item.quantity,
        volume = item.quantity * (types[item.typeId]?.volume ?: 0.0),
        sell = item.quantity * (prices[item.typeId]?.sell ?: 0.0),
        buy = item.quantity * (prices[item.typeId]?.buy ?: 0.0),
        updateTime = prices[item.typeId]?.updateTime ?: 0
    )


    private fun getBaseReactionShortList(query: List<ReactionQuery>): List<CompleteReactionShort> {

        val completeReactionsShort = mutableListOf<CompleteReactionShort>()

        query
            .filter { it.run > 0 }
            .forEach { build ->
            blueprintRepo.getById(build.bpcId)?.let { bpc ->

                val typeSet = mutableSetOf<Long>()
                val products = calcReactionItemForRun(bpc.products, build.run, 0.0)
                val materials = mutableListOf<ReactionItem>()
                val me = if (bpc.isFormula) 0.0 else build.me

                var subMaterials = calcReactionItemForRun(bpc.materials, build.run, me)

                do {
                    val subProducts = mutableListOf<ReactionItem>()
                    val baseMaterials = mutableListOf<ReactionItem>()

                    subMaterials.forEach { item ->
                        val subBpc = blueprintRepo.getById(item.typeId)

                        if (subBpc == null) baseMaterials.add(item)
                        else {
                            val subMe = if (subBpc.isFormula) 0.0 else build.subMe
                            val run = calculateRun(item, subBpc)
                            subProducts.addAll(calcReactionItemForRun(subBpc.materials, run, subMe))
                        }
                    }

                    subMaterials = subProducts
                    materials.addAll(baseMaterials)

                } while (subProducts.isNotEmpty())

                products.forEach { product -> typeSet.add(product.typeId) }
                materials.forEach { material -> typeSet.add(material.typeId) }

                val shorReaction = CompleteReactionShort(
                    products = products,
                    materials = materials,
                    typeIdSet = typeSet,
                    isFormula = bpc.isFormula,
                )
                completeReactionsShort.add(shorReaction)
            }
        }

        return completeReactionsShort.toList()
    }

    private fun getReactionShortList(query: List<ReactionQuery>): List<CompleteReactionShort> {

        val completeReactionsShort = mutableListOf<CompleteReactionShort>()

        query
            .filter { it.run > 0 }
            .forEach { build ->
            blueprintRepo.getById(build.bpcId)?.let { bpc ->

                val typeSet = mutableSetOf<Long>()
                val me = if (bpc.isFormula) 0.0 else build.me
                val products = calcReactionItemForRun(bpc.products, build.run, 0.0)
                val materials = calcReactionItemForRun(bpc.materials, build.run, me)

                products.forEach { product -> typeSet.add(product.typeId) }
                materials.forEach { material -> typeSet.add(material.typeId) }

                val shorReaction = CompleteReactionShort(
                    products = products,
                    materials = materials,
                    typeIdSet = typeSet,
                    isFormula = bpc.isFormula,
                )
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
        me: Double,
    ): List<ReactionItem> = items.map { item ->
        val meForItem = if (item.quantity == 1L) 1.0 else (1.0 - me / 100.0)
        val quantity = (item.quantity * run * meForItem)
        item.copy(quantity = ceil(quantity).toLong())
    }

}