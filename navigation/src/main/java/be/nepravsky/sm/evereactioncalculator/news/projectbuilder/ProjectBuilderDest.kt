package be.nepravsky.sm.evereactioncalculator.news.projectbuilder

import by.niaprauski.navigation.Dest
import kotlinx.serialization.Serializable

@Serializable
sealed class ProjectBuilderDest: Dest {

    companion object{
        fun root(projectId: Long?) = ProjectBuilder(projectId)
    }

    @Serializable
    data class ProjectBuilder(val projectId: Long?): ProjectBuilderDest()
}