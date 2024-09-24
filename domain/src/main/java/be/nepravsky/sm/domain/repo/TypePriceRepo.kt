package be.nepravsky.sm.domain.repo

import be.nepravsky.sm.domain.model.TypePrice

interface TypePriceRepo {

    fun getByIds(typeIds: List<Long>): List<TypePrice>

    fun updatePrice(newPrices: List<TypePrice>)

    suspend fun getRemoteById(typePrice: TypePrice): TypePrice
}