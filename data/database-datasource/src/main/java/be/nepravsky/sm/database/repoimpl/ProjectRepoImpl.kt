package be.nepravsky.sm.database.repoimpl

import app.cash.sqldelight.coroutines.asFlow
import app.cash.sqldelight.coroutines.mapToList
import be.nepravsky.sm.database.Projects
import be.nepravsky.sm.database.ProjectsTableQueries
import be.nepravsky.sm.domain.model.project.Project
import be.nepravsky.sm.domain.repo.ProjectRepo
import be.nepravsky.sm.domain.utils.DispatcherProvider
import kotlinx.coroutines.flow.Flow
import org.koin.core.annotation.Single

@Single
class ProjectRepoImpl(
    private val projectsTableQueries: ProjectsTableQueries,
    private val dispatcherProvider: DispatcherProvider,
) : ProjectRepo {


    override fun getAllWithoutItem(): Flow<List<Project>> =
        projectsTableQueries.getAll(
            mapper = { id: Long, name: String, description: String, iconId: Long ->
                Project(
                    id = id,
                    iconId = iconId,
                    name = name,
                    description = description,
                    items = emptyList(),
                )
            }
        )
            .asFlow()
            .mapToList(dispatcherProvider.io)


    override fun saveProject(project: Project) {
        val p = with(project) {
            Projects(
                id = id ?: 0,
                name = name,
                description = description,
                iconId = iconId
            )
        }
        projectsTableQueries.insert(projects = p)
    }

    override fun getMaxProjectId(): Long = projectsTableQueries
        .getMaxProjectId(mapper = { id: Long? -> id ?: 0 })
        .executeAsOne()
}