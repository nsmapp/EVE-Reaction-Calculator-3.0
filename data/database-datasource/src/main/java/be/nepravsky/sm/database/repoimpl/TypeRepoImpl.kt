package be.nepravsky.sm.database.repoimpl

import be.nepravsky.sm.database.TypeTableQueries
import be.nepravsky.sm.domain.model.Type
import be.nepravsky.sm.domain.repo.TypeRepo
import org.koin.core.annotation.Single


@Single(binds = [TypeRepo::class])
class TypeRepoImpl(
    private val typeTableQueries: TypeTableQueries
) : TypeRepo {


    override fun getByIds(ids: List<Long>): List<Type> = typeTableQueries
        .getByIds(ids) { id, basePrice, volume, name ->
            Type(
                id = id,
                basePrice = basePrice,
                volume = volume,
                name = name,
            )
        }
        .executeAsList()
}