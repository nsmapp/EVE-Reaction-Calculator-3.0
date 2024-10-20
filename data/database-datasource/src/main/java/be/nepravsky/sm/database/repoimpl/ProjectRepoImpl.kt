package be.nepravsky.sm.database.repoimpl

import app.cash.sqldelight.coroutines.asFlow
import app.cash.sqldelight.coroutines.mapToList
import be.nepravsky.sm.database.ProjectItemsTableQueries
import be.nepravsky.sm.database.Projects
import be.nepravsky.sm.database.ProjectsTableQueries
import be.nepravsky.sm.domain.model.project.Project
import be.nepravsky.sm.domain.model.project.ProjectItem
import be.nepravsky.sm.domain.repo.ProjectRepo
import be.nepravsky.sm.domain.utils.DispatcherProvider
import kotlinx.coroutines.flow.Flow
import org.koin.core.annotation.Single

@Single
class ProjectRepoImpl(
    private val projectsTableQueries: ProjectsTableQueries,
    private val projectItemsTableQueries: ProjectItemsTableQueries,
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
        projectItemsTableQueries.transaction {
            project.id?.let { id ->
                projectItemsTableQueries.deleteByProjectId(id)
                project.items.forEach { item ->
                    projectItemsTableQueries.insert(
                        id = null,
                        parentId = id,
                        reactionId = item.reactionId,
                        run = item.run,
                        me = item.me,
                        subMe = item.subMe,
                    )
                }
            }
        }

    }

    override fun getMaxProjectId(): Long = projectsTableQueries
        .getMaxProjectId(mapper = { id: Long? -> id ?: 0 })
        .executeAsOne()

    override fun getById(projectId: Long): Project {

        val items: List<ProjectItem> = projectItemsTableQueries.get(projectId,
            mapper = { id, parentId, reactionId, run, me, subMe, name ->
                ProjectItem(
                    reactionId = reactionId,
                    projectId = parentId,
                    run = run,
                    me = me,
                    subMe = subMe,
                    name = name,
                )
            }).executeAsList()

        val project = projectsTableQueries.getById(projectId,
            mapper = { id, name, description, iconId ->
                Project(
                    id = id,
                    iconId = iconId,
                    name = name,
                    description = description,
                    items = items
                )
            }).executeAsOne()


        return project
    }

    override fun deleteProject(projectId: Long) {
        projectsTableQueries.delete(projectId)
        projectItemsTableQueries.deleteByProjectId(projectId)
    }
}