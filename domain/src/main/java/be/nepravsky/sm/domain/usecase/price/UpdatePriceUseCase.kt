package be.nepravsky.sm.domain.usecase.price

import be.nepravsky.sm.domain.model.BpcFull
import be.nepravsky.sm.domain.model.price.TypePrice
import be.nepravsky.sm.domain.repo.BlueprintRepo
import be.nepravsky.sm.domain.repo.TypePriceRepo
import be.nepravsky.sm.domain.utils.DispatcherProvider
import kotlinx.coroutines.async
import kotlinx.coroutines.awaitAll
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.withContext
import org.koin.core.annotation.Factory


@Factory
class UpdatePriceUseCase(
    private val blueprintRepo: BlueprintRepo,
    private val priceRepo: TypePriceRepo,
    private val dispatcherProvider: DispatcherProvider,
) {
    private val time = System.currentTimeMillis()
    private val minTimeForUpdate = 3_600_000

    //TODO add check offline mode
    suspend fun invoke(reactionId: Long): Result<Int> =
        withContext(dispatcherProvider.io) {
            runCatching {
                val bpc = blueprintRepo.getById(reactionId)
                update(bpc).size
            }
        }


    private suspend fun update(bpcFull: BpcFull?): List<TypePrice> {

        bpcFull ?: return emptyList()

        return coroutineScope {

            val itemsIds = getItemIds(bpcFull)

            val priceForUpdate = priceRepo.getByIds(itemsIds)
                .filter { type -> (time - type.updateTime) > minTimeForUpdate }
                .map { type -> async { priceRepo.getRemoteById(type) } }

            val newPrices = priceForUpdate.awaitAll()
            priceRepo.updatePrice(newPrices)

            return@coroutineScope newPrices
        }

    }


    private fun getItemIds(
        bpcFull: BpcFull,
    ): List<Long> {

        val itemSet = mutableSetOf<Long>()
        bpcFull.products.forEach { item -> itemSet.add(item.typeId) }
        var materials = bpcFull.materials

        do {
            itemSet.addAll(materials.map { item -> item.typeId })
            val subProduct = materials.mapNotNull { item -> blueprintRepo.getById(item.typeId) }
            materials = subProduct.map { items -> items.materials }
                .flatten()

        } while (subProduct.isNotEmpty())

        return itemSet.toList()
    }
}