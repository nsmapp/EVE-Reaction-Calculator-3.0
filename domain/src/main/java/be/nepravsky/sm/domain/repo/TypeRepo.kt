package be.nepravsky.sm.domain.repo

import be.nepravsky.sm.domain.model.Type

interface TypeRepo {

    fun getByIds(ids: List<Long>): List<Type>
}