package be.nepravsky.sm.domain.usecase.project

import be.nepravsky.sm.domain.model.project.Project
import be.nepravsky.sm.domain.repo.ProjectRepo
import be.nepravsky.sm.domain.utils.DispatcherProvider
import kotlinx.coroutines.withContext
import org.koin.core.annotation.Factory


@Factory
class GetProjectUseCase(
    private val projectRepo: ProjectRepo,
    private val dispatcherProvider: DispatcherProvider,
) {

    suspend fun invoke(projectId: Long): Result<Project> =
        withContext(dispatcherProvider.io) {
            runCatching {
                projectRepo.getById(projectId)
            }
        }
}