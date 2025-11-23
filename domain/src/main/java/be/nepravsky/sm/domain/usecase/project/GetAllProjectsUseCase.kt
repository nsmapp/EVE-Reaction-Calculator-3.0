package be.nepravsky.sm.domain.usecase.project

import be.nepravsky.sm.domain.model.project.Project
import be.nepravsky.sm.domain.repo.ProjectRepo
import be.nepravsky.sm.domain.utils.DispatcherProvider
import kotlinx.collections.immutable.ImmutableList
import kotlinx.collections.immutable.toPersistentList
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.map
import org.koin.core.annotation.Factory


@Factory
class GetAllProjectsUseCase(
    private val projectRepo: ProjectRepo,
    private val dispatcherProvider: DispatcherProvider,
) {

    fun invoke(): Flow<ImmutableList<Project>> = projectRepo
        .getAllWithoutItem()
        .map { projects -> projects.toPersistentList() }
        .flowOn(dispatcherProvider.io)
}