package be.nepravsky.sm.evereactioncalculator.mapper

import be.nepravsky.sm.domain.model.ComplexReaction
import be.nepravsky.sm.domain.model.ReactionItemFull
import be.nepravsky.sm.domain.utils.toISK
import be.nepravsky.sm.domain.utils.toVolume
import be.nepravsky.sm.evereactioncalculator.model.ComplexReactionModel
import be.nepravsky.sm.evereactioncalculator.model.ReactionItemModel
import org.koin.core.annotation.Factory

@Factory
class ComplexReactionMapper {

    fun map(reaction: ComplexReaction): ComplexReactionModel =
        with(reaction) {

            val products: List<ReactionItemModel> = reaction.reactions
                .map { it.products }
                .run { mapReactionItems(this, true) }
            val productPriceDif = reaction.productSell - reaction.productBuy

            val materials: List<ReactionItemModel> = reaction.reactions
                .map { it.materials }
                .run { mapReactionItems(this, false) }
            val materialPriceDif = reaction.materialSell - reaction.materialBuy


            ComplexReactionModel(
                items = products + materials,
                productQuantity = productQuantity.toString(),
                productVolume = productVolume.toVolume(),
                productSell = productSell.toISK(),
                productBuy = productBuy.toISK(),
                productPriceDif = productPriceDif.toISK(),
                materialQuantity = materialQuantity.toString(),
                materialVolume = materialVolume.toVolume(),
                materialSell = materialSell.toISK(),
                materialBuy = materialBuy.toISK(),
                materialPriceDif = materialPriceDif.toISK()
            )
        }

    private fun mapReactionItems(
        reactionItems: List<List<ReactionItemFull>>,
        isProduct: Boolean
    ): List<ReactionItemModel> = reactionItems
        .flatten()
        .groupBy { it.id }.values
        .map { items ->
            ReactionItemModel(
                id = items.first().id,
                quantity = items.sumOf { it.quantity }.toString(),
                name = items.first().name,
                volume = items.sumOf { it.volume }.toVolume(),
                buy = items.sumOf { it.buy }.toISK(),
                sell = items.sumOf { it.sell }.toISK(),
                isProduct = isProduct,
            )
        }
}