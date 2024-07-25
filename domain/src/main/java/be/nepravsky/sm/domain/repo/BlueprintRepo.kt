package be.nepravsky.sm.domain.repo

import be.nepravsky.sm.domain.model.Blueprint
import be.nepravsky.sm.domain.query.ReactionsQuery
import kotlinx.coroutines.flow.Flow

interface BlueprintRepo {


    fun get(query: ReactionsQuery): List<Blueprint>
}