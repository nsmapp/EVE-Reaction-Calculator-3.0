package be.nepravsky.sm.domain.usecase.price

import be.nepravsky.sm.domain.model.BpcFull
import be.nepravsky.sm.domain.model.TypePrice
import be.nepravsky.sm.domain.model.settings.PriceSource
import be.nepravsky.sm.domain.repo.BlueprintRepo
import be.nepravsky.sm.domain.repo.SettingRepo
import be.nepravsky.sm.domain.repo.TypePriceRepo
import be.nepravsky.sm.domain.utils.DispatcherProvider
import kotlinx.coroutines.async
import kotlinx.coroutines.awaitAll
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.withContext
import org.koin.core.annotation.Factory


@Factory
class UpdatePriceUseCase(
    private val blueprintRepo: BlueprintRepo,
    private val priceRepo: TypePriceRepo,
    private val dispatcherProvider: DispatcherProvider,
    private val settingRepo: SettingRepo,
) {
    private val emptyListSize = 0
    private val time = System.currentTimeMillis()
    private val minTimeForUpdate = 3_600_000
    private val requestDelay = 100L
    private val requestChunk = 5

    suspend fun invoke(reactionId: Long): Result<Int> =
        withContext(dispatcherProvider.io) {
            runCatching {
                val priceSource = settingRepo.getPriceSource()
                if (priceSource == PriceSource.OFFLINE) return@runCatching emptyListSize

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

            val prices = mutableListOf<TypePrice>()
            priceForUpdate
                .chunked(requestChunk)
                .forEach { chunk ->
                    delay(requestDelay)
                    val priceChunk = chunk.awaitAll()
                    priceRepo.updatePrice(priceChunk)
                    prices.addAll(priceChunk)
                }

            return@coroutineScope prices
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