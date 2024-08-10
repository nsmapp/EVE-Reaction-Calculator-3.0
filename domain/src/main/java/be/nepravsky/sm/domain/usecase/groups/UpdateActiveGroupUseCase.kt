package be.nepravsky.sm.domain.usecase.groups

import be.nepravsky.sm.domain.model.query.ActiveGroupQuery
import be.nepravsky.sm.domain.repo.ReactionGroupRepository
import be.nepravsky.sm.domain.utils.DispatcherProvider
import kotlinx.coroutines.withContext
import org.koin.core.annotation.Factory

@Factory
class UpdateActiveGroupUseCase(
    private val reactionGroupRepository: ReactionGroupRepository,
    private val dispatcherProvider: DispatcherProvider,
) {

    suspend fun invoke(query: ActiveGroupQuery): Result<Unit> =
        withContext(dispatcherProvider.io) {
            runCatching {
                reactionGroupRepository.updateActiveGroups(query)
            }
        }
}