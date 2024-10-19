package be.nepravsky.sm.domain.usecase.project

import be.nepravsky.sm.domain.model.project.Project
import be.nepravsky.sm.domain.repo.ProjectRepo
import be.nepravsky.sm.domain.utils.DispatcherProvider
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOn
import org.koin.core.annotation.Factory


@Factory
class GetAllProjectsUseCase(
    private val projectRepo: ProjectRepo,
    private val dispatcherProvider: DispatcherProvider,
){

    fun invoke(): Flow<List<Project>> = projectRepo
        .getAllWithoutItem()
        .flowOn(dispatcherProvider.io)
}