package be.nepravsky.sm.evereactioncalculator.mapper

import be.nepravsky.sm.evereactioncalculator.model.ComplexReactionModel
import be.nepravsky.sm.evereactioncalculator.model.ReactionItemModel
import org.koin.core.annotation.Factory

@Factory
class SharedReactionMapper {

    fun getSimpleText(model: ComplexReactionModel, isBaseMaterials: Boolean): String =
        mapSimpleReaction(model, isBaseMaterials, true)

    fun getRichText(model: ComplexReactionModel, isBaseMaterials: Boolean): String =
        mapSimpleReaction(model, isBaseMaterials, false)

    private fun mapSimpleReaction(
        model: ComplexReactionModel,
        isBaseMaterials: Boolean,
        isSimpleText: Boolean,
    ): String {

        val sb = StringBuilder()

        val productVolume = if (isBaseMaterials) model.productVolume else model.fullProductVolume
        val productSell = if (isBaseMaterials) model.productSell else model.fullProductSell
        val productBuy = if (isBaseMaterials) model.productBuy else model.fullProductBuy

        val materialVolume = if (isBaseMaterials) model.materialVolume else model.fullMaterialVolume
        val materialSell = if (isBaseMaterials) model.materialSell else model.fullMaterialSell
        val materialBuy = if (isBaseMaterials) model.materialBuy else model.fullMaterialBuy

        val items = if (isBaseMaterials) model.baseItems else model.items

        sb.appendLine("Products:")
            .appendLine("Volume: $productVolume sell: $productSell  buy: $productBuy")
            .appendLine("Materials:")
            .appendLine("Volume: $materialVolume sell: $materialSell  buy: $materialBuy")
            .appendLine()

        addItems(isSimpleText, items, sb)

        return sb.toString()

    }

    private fun addItems(
        isSimpleText: Boolean,
        items: List<ReactionItemModel>,
        sb: StringBuilder
    ) {
        items.forEach { item ->
            val i = if (isSimpleText) createSimpleItem(item) else createRichItem(item)
            sb.appendLine(i)
        }
    }

    private fun createRichItem(item: ReactionItemModel): String =
        "${item.quantity} x <url=showinfo:${item.id}>${item.name}</url>"

    private fun createSimpleItem(item: ReactionItemModel): String =
        "${item.quantity} x ${item.name}"
}