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

            val baseProducts: List<ReactionItemModel> = reaction.baseReactions
                .map { it.products }
                .run { mapReactionItems(this, true) }
                .sortedBy { it.groupId }
            val baseProductPriceDif = reaction.productSell - reaction.productBuy

            val baseMaterials: List<ReactionItemModel> = reaction.baseReactions
                .map { it.materials }
                .run { mapReactionItems(this, false) }
                .sortedBy { it.groupId }
            val baseMaterialPriceDif = reaction.materialSell - reaction.materialBuy

            val products: List<ReactionItemModel> = reaction.reactions
                .map { it.products }
                .run { mapReactionItems(this, true) }
                .sortedBy { it.groupId }
            val productPriceDif = reaction.fullProductSell - reaction.fullProductBuy

            val materials: List<ReactionItemModel> = reaction.reactions
                .map { it.materials }
                .run { mapReactionItems(this, false) }
                .sortedBy { it.groupId }
            val materialPriceDif = reaction.fullMaterialSell - reaction.fullMaterialBuy


            ComplexReactionModel(
                baseItems = baseProducts + baseMaterials,
                productQuantity = productQuantity.toString(),
                productVolume = productVolume.toVolume(),
                productSell = productSell.toISK(),
                productBuy = productBuy.toISK(),
                productPriceDif = baseProductPriceDif.toISK(),
                materialQuantity = materialQuantity.toString(),
                materialVolume = materialVolume.toVolume(),
                materialSell = materialSell.toISK(),
                materialBuy = materialBuy.toISK(),
                materialPriceDif = baseMaterialPriceDif.toISK(),

                items = products + materials,
                fullProductQuantity = fullProductQuantity.toString(),
                fullProductVolume = fullProductVolume.toVolume(),
                fullProductSell = fullProductSell.toISK(),
                fullProductBuy = fullProductBuy.toISK(),
                fullProductPriceDif = productPriceDif.toISK(),
                fullMaterialQuantity = fullMaterialQuantity.toString(),
                fullMaterialVolume = fullMaterialVolume.toVolume(),
                fullMaterialSell = fullMaterialSell.toISK(),
                fullMaterialBuy = fullMaterialBuy.toISK(),
                fullMaterialPriceDif = materialPriceDif.toISK(),
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
                groupId = items.first().groupId,
                quantity = items.sumOf { it.quantity }.toString(),
                name = items.first().name,
                volume = items.sumOf { it.volume }.toVolume(),
                buy = items.sumOf { it.buy }.toISK(),
                sell = items.sumOf { it.sell }.toISK(),
                isProduct = isProduct,
            )
        }
}