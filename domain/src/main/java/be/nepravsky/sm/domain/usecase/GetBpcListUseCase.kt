package be.nepravsky.sm.domain.usecase

import be.nepravsky.sm.domain.model.Blueprint
import be.nepravsky.sm.domain.query.ReactionsQuery
import be.nepravsky.sm.domain.repo.BlueprintRepo
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import org.koin.core.annotation.Factory


@Factory
class GetBpcListUseCase(
    private val blueprintRepo: BlueprintRepo
) {

    suspend fun invoke(query: ReactionsQuery): Result<List<Blueprint>> =
        withContext(Dispatchers.IO) {
            runCatching { blueprintRepo.get(query) }
        }
}