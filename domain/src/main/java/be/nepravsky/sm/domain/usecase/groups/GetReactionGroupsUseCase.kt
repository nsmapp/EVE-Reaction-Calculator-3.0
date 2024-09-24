package be.nepravsky.sm.domain.usecase.groups

import be.nepravsky.sm.domain.model.ReactionGroup
import be.nepravsky.sm.domain.repo.ReactionGroupRepo
import be.nepravsky.sm.domain.utils.DispatcherProvider
import kotlinx.coroutines.withContext
import org.koin.core.annotation.Factory


@Factory
class GetReactionGroupsUseCase(
    private val reactionGroupRepo: ReactionGroupRepo,
    private val dispatcherProvider: DispatcherProvider,
) {


    suspend fun invoke(): Result<List<ReactionGroup>> =
        withContext(dispatcherProvider.io){
            runCatching {
                reactionGroupRepo.getAll()
            }
        }
}