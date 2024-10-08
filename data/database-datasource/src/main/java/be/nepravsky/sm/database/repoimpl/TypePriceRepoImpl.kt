package be.nepravsky.sm.database.repoimpl

import be.nepravsky.sm.database.TypePriceTableQueries
import be.nepravsky.sm.database.Type_price
import be.nepravsky.sm.database.network.PriceSource
import be.nepravsky.sm.domain.model.TypePrice
import be.nepravsky.sm.domain.repo.TypePriceRepo
import org.koin.core.annotation.Single


@Single(binds = [TypePriceRepo::class])
class TypePriceRepoImpl(
    private val typePriceTableQueries: TypePriceTableQueries,
    private val priceSource: PriceSource,
) : TypePriceRepo {

    override fun getByIds(typeIds: List<Long>): List<TypePrice> = typePriceTableQueries
        .getPriceByIds(typeIds)
        .executeAsList()
        .map { type ->
            with(type) {
                TypePrice(
                    id = typeId,
                    systemId = systemId,
                    regionId = regionId,
                    sell = sell,
                    buy = buy,
                    updateTime = updateTime,
                )
            }
        }

    override fun updatePrice(newPrices: List<TypePrice>) {
        newPrices.map { item ->
            with(item) {
                Type_price(
                    typeId = id,
                    systemId = systemId,
                    regionId = regionId,
                    sell = sell,
                    buy = buy,
                    updateTime = updateTime
                )
            }
        }.also { prices ->
            typePriceTableQueries.transaction {
                prices.forEach { price ->
                    typePriceTableQueries.insert(price)
                }
            }
        }
    }

    override suspend fun getRemoteById(typePrice: TypePrice): TypePrice {
        val prices = priceSource.getPrice(typePrice.id, typePrice.regionId)
        val sell = prices.filter { item -> item.isBuyOrder.not() }
            .minByOrNull { it.price }?.price ?: 0.0

        val buy = prices.filter { item -> item.isBuyOrder }
            .maxByOrNull { it.price }?.price ?: 0.0

        return typePrice.copy(
            sell = sell,
            buy = buy,
            updateTime = System.currentTimeMillis()
        )
    }
}