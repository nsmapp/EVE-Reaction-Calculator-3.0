package be.nepravsky.sm.database.network

import be.nepravsky.sm.database.network.model.response.PriceResponse

interface PriceSource {

    suspend fun getPrice(itemId: Long,  regionId: Long): List<PriceResponse>

}