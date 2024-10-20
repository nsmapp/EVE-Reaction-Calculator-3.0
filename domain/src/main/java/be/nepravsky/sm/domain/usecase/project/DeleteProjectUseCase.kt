package be.nepravsky.sm.domain.usecase.project

import be.nepravsky.sm.domain.repo.ProjectRepo
import be.nepravsky.sm.domain.utils.DispatcherProvider
import kotlinx.coroutines.withContext
import org.koin.core.annotation.Factory


@Factory
class DeleteProjectUseCase(
    private val projectRepo: ProjectRepo,
    private val dispatcherProvider: DispatcherProvider,
) {

    suspend fun invoke(projectId: Long): Result<Unit> =
        withContext(dispatcherProvider.io) {
            runCatching {
                projectRepo.deleteProject(projectId)
            }
        }
}