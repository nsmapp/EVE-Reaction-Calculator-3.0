package be.nepravsky.sm.evereactioncalculator.mapper

import be.nepravsky.sm.domain.model.project.Project
import be.nepravsky.sm.domain.model.query.ReactionQuery
import org.koin.core.annotation.Factory

@Factory
class ProjectMapper {

    fun mapToReactionQuery(project: Project): List<ReactionQuery> =
        with(project) {

            items.map { item ->
                ReactionQuery(
                    bpcId = item.reactionId,
                    me = item.me,
                    subMe = item.subMe,
                    run = item.run,
                )
            }

        }
}