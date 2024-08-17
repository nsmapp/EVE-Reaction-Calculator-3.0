package be.nepravsky.sm.database.network

import be.nepravsky.sm.database.network.model.response.PriceResponse
import io.ktor.client.call.body
import io.ktor.client.request.get
import io.ktor.http.path
import io.ktor.util.StringValues
import org.koin.core.annotation.Single


@Single(binds = [PriceSource::class])
class PriceSourceImpl(
    private val ktor: KtorClient,
): PriceSource {

    companion object {
        const val DEFAULT_SOURCE = "tranquility"
        const val DEFAULT_ORDER_TYPE = "all"
    }

    override suspend fun getPrice(itemId: Long, regionId: Long): List<PriceResponse>  =
        ktor.client.get {
            url {
                path("latest/markets/$regionId/orders/")
                parameters.appendAll(
                    StringValues.build {
                        append("type_id", itemId.toString())
                        append("datasource", DEFAULT_SOURCE)
                        append("order_type", DEFAULT_ORDER_TYPE)
                    }
                )
            }
        }.body()
}