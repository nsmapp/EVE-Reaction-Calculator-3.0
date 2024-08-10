package be.nepravsky.sm.domain.usecase.groups

import be.nepravsky.sm.domain.model.ReactionGroup
import be.nepravsky.sm.domain.repo.ReactionGroupRepository
import be.nepravsky.sm.domain.utils.DispatcherProvider
import kotlinx.coroutines.withContext
import org.koin.core.annotation.Factory


@Factory
class GetReactionGroupsUseCase(
    private val reactionGroupRepository: ReactionGroupRepository,
    private val dispatcherProvider: DispatcherProvider,
) {


    suspend fun invoke(): Result<List<ReactionGroup>> =
        withContext(dispatcherProvider.io){
            runCatching {
                reactionGroupRepository.getAll()
            }
        }
}