package be.nepravsky.sm.domain.usecase.groups

import be.nepravsky.sm.domain.repo.ReactionGroupRepo
import kotlinx.coroutines.flow.Flow
import org.koin.core.annotation.Factory


@Factory
class GetActiveGroupIdsUseCase(
    private val reactionGroupRepo: ReactionGroupRepo,
){

    fun invoke(): Flow<List<Long>> = reactionGroupRepo
        .getActiveGroupId()
}