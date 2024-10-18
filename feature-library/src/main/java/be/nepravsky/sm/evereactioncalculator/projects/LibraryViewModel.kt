package be.nepravsky.sm.evereactioncalculator.projects

import be.nepravsky.sm.evereactioncalculator.projects.model.LibraryState
import be.nepravsky.sm.evereactioncalculator.projects.model.ProjectModel
import be.nepravsky.sm.evereactioncalculator.viewmodel.BaseViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import org.koin.core.annotation.Factory

@Factory(binds = [BaseViewModel::class])
class LibraryViewModel: BaseViewModel(), LibraryContract{

    private val _state = MutableStateFlow(LibraryState.EMPTY)
    val state = _state.asStateFlow()


    override fun loadProjects() {
        _state.update {
            it.copy(
                projects = listOf(
                    ProjectModel(1, "test", 34),
                    ProjectModel(2, "test effes", 35),
                    ProjectModel(3, "tessfese oruh iunn un  osfnsoin fn osnveoft", 37),
                    ProjectModel(4, "t   est", 666),
                    ProjectModel(5, "tessfefset", 978),
                )
            )
        }
    }

    override fun addProject() {
    }

    override fun editProject(projectId: Long) {
    }

    override fun runProject(projectId: Long) {
    }

    override fun deleteProject(projectId: Long) {
        
    }
}