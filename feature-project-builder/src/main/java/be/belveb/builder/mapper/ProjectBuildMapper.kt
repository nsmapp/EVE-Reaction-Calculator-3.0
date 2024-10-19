package be.belveb.builder.mapper

import android.os.SystemClock
import be.belveb.builder.model.ProjectBuilderState
import be.nepravsky.sm.domain.model.project.Project
import be.nepravsky.sm.evereactioncalculator.utils.TEXT_EMPTY
import org.koin.core.annotation.Factory

@Factory
class ProjectBuildMapper {


    fun mapProject(model: ProjectBuilderState): Project {

        val projectName = model.name.ifEmpty { SystemClock.currentThreadTimeMillis().toString() }

        return with(model) {
            Project(
                id = id,
                //TODO replace project icon
                iconId = 34,
                name = projectName,
                description = TEXT_EMPTY,
                //TODO add items
                items = emptyList()
            )
        }
    }
}