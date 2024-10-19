package be.nepravsky.sm.domain.usecase.project

import be.nepravsky.sm.domain.model.project.Project
import be.nepravsky.sm.domain.repo.ProjectRepo
import be.nepravsky.sm.domain.utils.DispatcherProvider
import kotlinx.coroutines.withContext
import org.koin.core.annotation.Factory

@Factory
class SaveProjectUseCase(
    private val dispatcherProvider: DispatcherProvider,
    private val projectRepo: ProjectRepo,
) {

    suspend fun save(project: Project): Result<Unit> =
        withContext(dispatcherProvider.io) {
            runCatching {
                val projectId = project.id ?: (projectRepo.getMaxProjectId() + 1)
                projectRepo.saveProject(project.copy(id = projectId))
            }
        }
}