package be.nepravsky.sm.domain.usecase.groups

import be.nepravsky.sm.domain.repo.ReactionGroupRepository
import kotlinx.coroutines.flow.Flow
import org.koin.core.annotation.Factory


@Factory
class GetActiveGroupIdsUseCase(
    private val reactionGroupRepository: ReactionGroupRepository,
){

    fun invoke(): Flow<List<Long>> = reactionGroupRepository
        .getActiveGroupId()
}