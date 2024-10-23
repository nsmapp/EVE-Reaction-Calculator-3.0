package be.nepravsky.sm.domain.usecase.price

import be.nepravsky.sm.domain.model.bpc.BpcFull
import be.nepravsky.sm.domain.model.TypePrice
import be.nepravsky.sm.domain.model.settings.PriceSource
import be.nepravsky.sm.domain.repo.BlueprintRepo
import be.nepravsky.sm.domain.repo.SettingRepo
import be.nepravsky.sm.domain.repo.TypePriceRepo
import be.nepravsky.sm.domain.utils.DispatcherProvider
import kotlinx.coroutines.Deferred
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

    suspend fun invoke(reactionIds: List<Long>): Result<Int> =
        withContext(dispatcherProvider.io) {
            runCatching {
                val priceSource = settingRepo.getPriceSource()
                if (priceSource == PriceSource.OFFLINE) return@runCatching emptyListSize

                val bpc = blueprintRepo.getByIds(reactionIds)
                update(bpc).size
            }
        }


    private suspend fun update(bpcList: List<BpcFull>): List<TypePrice> {

        return coroutineScope {
            val defRegion = settingRepo.getDefaultRegionId()
            val defSystem = settingRepo.getDefaultSolarSystemId()

            val typeIds = getTypeIds(bpcList)

            val pricesExist = priceRepo.getByIds(typeIds)
            val existTypeIds = pricesExist.map { price -> price.id }
            val pricesNotExist = typeIds
                .filter { typeId -> typeId !in existTypeIds }
                .map { typeId ->
                    TypePrice(
                        id = typeId,
                        systemId = defSystem, regionId = defRegion,
                        sell = 0.0,
                        buy = 0.0,
                        updateTime = 0
                    )
                }

            val allPrices = (pricesExist + pricesNotExist)
                .filter { type -> (time - type.updateTime) > minTimeForUpdate }
                .map { type -> async { priceRepo.getRemoteById(type) } }

            val prices = requestPrices(allPrices)

            return@coroutineScope prices
        }

    }

    private suspend fun requestPrices(allPrices: List<Deferred<TypePrice>>): MutableList<TypePrice> {
        val prices = mutableListOf<TypePrice>()

        allPrices
            .chunked(requestChunk)
            .forEach { chunk ->
                delay(requestDelay)

                val priceChunk = chunk.awaitAll()

                priceRepo.updatePrice(priceChunk)
                prices.addAll(priceChunk)
            }

        return prices
    }


    private fun getTypeIds(
        bpcList: List<BpcFull>,
    ): List<Long> {

        val itemSet = mutableSetOf<Long>()

        bpcList.forEach { bpc ->
            bpc.products.forEach { item -> itemSet.add(item.typeId) }
            var materials = bpc.materials

            do {
                itemSet.addAll(materials.map { item -> item.typeId })
                val subProduct = materials.mapNotNull { item -> blueprintRepo.getById(item.typeId) }
                materials = subProduct.map { items -> items.materials }
                    .flatten()

            } while (subProduct.isNotEmpty())
        }


        return itemSet.toList()
    }
}