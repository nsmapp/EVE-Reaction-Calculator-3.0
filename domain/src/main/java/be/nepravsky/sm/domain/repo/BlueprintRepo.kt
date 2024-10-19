package be.nepravsky.sm.domain.repo

import be.nepravsky.sm.domain.model.bpc.BpcFull
import be.nepravsky.sm.domain.model.bpc.BpcShort
import be.nepravsky.sm.domain.model.query.ReactionsQuery

interface BlueprintRepo {


    fun get(query: ReactionsQuery): List<BpcShort>

    fun getById(reactionId: Long): BpcFull?

    fun getByIds(reactionIds: List<Long>): List<BpcFull>
}