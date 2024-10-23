package be.nepravsky.sm.evereactioncalculator.mapper

import be.nepravsky.sm.domain.model.ComplexReaction
import be.nepravsky.sm.domain.model.ReactionItemFull
import be.nepravsky.sm.evereactioncalculator.utils.toISK
import be.nepravsky.sm.evereactioncalculator.utils.toVolume
import be.nepravsky.sm.evereactioncalculator.model.ComplexReactionModel
import be.nepravsky.sm.evereactioncalculator.model.ReactionItemModel
import org.koin.core.annotation.Factory
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

@Factory
class ComplexReactionMapper {

    private val sdf = SimpleDateFormat("yyyy.MM.dd hh:mm", Locale.getDefault())

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

            val baseItems = baseProducts + baseMaterials

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
            val items =  products + materials

            val hasZeroPrice = baseItems.any { it.hasZeroPrice } || items.any { it.hasZeroPrice }

            ComplexReactionModel(
                baseItems = baseItems,
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

                items = items,
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

                hasZeroPrice = hasZeroPrice
            )
        }

    private fun mapReactionItems(
        reactionItems: List<List<ReactionItemFull>>,
        isProduct: Boolean
    ): List<ReactionItemModel> = reactionItems
        .flatten()
        .groupBy { it.id }.values
        .map { items ->

            val buy = items.sumOf { it.buy }
            val sell = items.sumOf { it.sell }
            val updateTime = if (items.first().updateTime == 0L) "-"
            else sdf.format(Date(items.first().updateTime))

            val hasZeroPrice = buy <= 0.0 || sell <= 0.0

            ReactionItemModel(
                id = items.first().id,
                groupId = items.first().groupId,
                quantity = items.sumOf { it.quantity }.toString(),
                name = items.first().name,
                volume = items.sumOf { it.volume }.toVolume(),
                buy = buy.toISK(),
                sell = sell.toISK(),
                isProduct = isProduct,
                updateTime = updateTime,
                hasZeroPrice = hasZeroPrice
            )
        }
}