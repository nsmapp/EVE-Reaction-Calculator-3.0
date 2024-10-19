package be.nepravsky.sm.domain.repo

import be.nepravsky.sm.domain.model.project.Project
import kotlinx.coroutines.flow.Flow

interface ProjectRepo {

    fun getAllWithoutItem(): Flow<List<Project>>

    fun saveProject(project: Project)

    fun getMaxProjectId(): Long
}