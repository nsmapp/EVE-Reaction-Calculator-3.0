package be.nepravsky.sm.evereactioncalculator.projects

import androidx.lifecycle.viewModelScope
import be.nepravsky.sm.domain.model.project.Project
import be.nepravsky.sm.domain.usecase.project.DeleteProjectUseCase
import be.nepravsky.sm.domain.usecase.project.GetAllProjectsUseCase
import be.nepravsky.sm.evereactioncalculator.projects.mapper.ProjectMapper
import be.nepravsky.sm.evereactioncalculator.projects.model.LibraryState
import be.nepravsky.sm.evereactioncalculator.viewmodel.BaseViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import org.koin.core.annotation.Factory

@Factory(binds = [BaseViewModel::class])
class LibraryViewModel(
    private val getAllProjectsUseCase: GetAllProjectsUseCase,
    private val projectMapper: ProjectMapper,
    private val deleteProjectUseCase: DeleteProjectUseCase,
) : BaseViewModel(), LibraryContract {

    private val _state = MutableStateFlow(LibraryState.EMPTY)
    val state = _state.asStateFlow()


    override fun addProject() {
    }

    override fun editProject(projectId: Long) {
    }

    override fun runProject(projectId: Long) {
    }

    override fun deleteProject(projectId: Long) {
        viewModelScope.launch {
            deleteProjectUseCase.invoke(projectId)
                .onSuccess {  }
        }
    }

    override fun getAllWithoutItems(){
        viewModelScope.launch {
            getAllProjectsUseCase.invoke()
                .collect{ projects -> handleProjects(projects) }
        }
    }

    private fun handleProjects(projects: List<Project>) {
        val models = projects.map { project ->
            projectMapper.map(project)
        }
        _state.update { it.copy(projects = models) }
    }

}