package be.nepravsky.sm.domain.usecase

import be.nepravsky.sm.domain.model.bpc.BpcShort
import be.nepravsky.sm.domain.model.query.ReactionsQuery
import be.nepravsky.sm.domain.repo.BlueprintRepo
import be.nepravsky.sm.domain.utils.DispatcherProvider
import kotlinx.coroutines.withContext
import org.koin.core.annotation.Factory


@Factory
class GetBpcListUseCase(
    private val blueprintRepo: BlueprintRepo,
    private val dispatcherProvider: DispatcherProvider,
) {

    suspend fun invoke(query: ReactionsQuery): Result<List<BpcShort>> =
        withContext(dispatcherProvider.io) {
            runCatching { blueprintRepo.get(query) }
        }
}