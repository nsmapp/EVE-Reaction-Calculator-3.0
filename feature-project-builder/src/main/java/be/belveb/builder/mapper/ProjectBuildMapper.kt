package be.belveb.builder.mapper

import android.os.SystemClock
import be.belveb.builder.model.ProjectBuilderState
import be.belveb.builder.model.ProjectItemModel
import be.nepravsky.sm.domain.model.project.Project
import be.nepravsky.sm.domain.model.project.ProjectItem
import be.nepravsky.sm.evereactioncalculator.utils.TEXT_EMPTY
import org.koin.core.annotation.Factory

@Factory
class ProjectBuildMapper {

    fun map(project: Project): ProjectBuilderState =
        with(project) {
            ProjectBuilderState(
                id = id,
                name = name,
                items = mapItems(items),
                isShowTypeBottomSheet = false,
                types = emptyList(),
            )
        }

    private fun mapItems(items: List<ProjectItem>): MutableList<ProjectItemModel>  =
        items.map { item ->
            ProjectItemModel(
                id = item.reactionId,
                name = item.name,
                runCount = item.run.toString(),
                me = item.me,
                subMe = item.subMe
            )
        }.toMutableList()


    fun mapProject(model: ProjectBuilderState): Project {

        val projectName = model.name.ifEmpty { SystemClock.currentThreadTimeMillis().toString() }

        return with(model) {
            Project(
                id = id,
                iconId = items.firstOrNull()?.id ?: 34,
                name = projectName,
                description = TEXT_EMPTY,
                items = items.map { item ->

                    val run = try {
                        item.runCount.toLong()
                    } catch (e: Exception) {
                        1
                    }

                    ProjectItem(
                        reactionId = item.id,
                        projectId = model.id,
                        run = run,
                        me = item.me,
                        subMe = item.subMe,
                        name = item.name,
                    )
                }
            )
        }
    }
}