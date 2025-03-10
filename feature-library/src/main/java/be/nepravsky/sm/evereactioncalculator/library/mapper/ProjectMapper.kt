package be.nepravsky.sm.evereactioncalculator.library.mapper

import be.nepravsky.sm.domain.model.project.Project
import be.nepravsky.sm.evereactioncalculator.library.model.ProjectModel
import org.koin.core.annotation.Factory

@Factory
class ProjectMapper {

    fun map(project: Project): ProjectModel =
        with(project){
            ProjectModel(
                id = id ?: 0,
                name = name,
                iconId = iconId,
            )
        }
}