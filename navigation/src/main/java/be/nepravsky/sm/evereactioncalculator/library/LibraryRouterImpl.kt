package be.nepravsky.sm.evereactioncalculator.library

import androidx.compose.runtime.Composable
import be.nepravsky.sm.evereactioncalculator.projects.LibraryRouter
import be.nepravsky.sm.evereactioncalculator.projects.LibraryScreen
import be.nepravsky.sm.evereactioncalculator.projects.LibraryViewModel
import be.nepravsky.sm.evereactioncalculator.navigation.Rout
import be.nepravsky.sm.evereactioncalculator.viewmodel.viewModelKey
import com.arkivanov.decompose.ComponentContext

class LibraryRouterImpl(
    componentContext: ComponentContext,
    private val onAddProject: (projectId: Long?) -> Unit,
    private val onRunProject: (projectId: Long) -> Unit,
): Rout(
    componentContext = componentContext,
    viewModelKey = LibraryViewModel::class.viewModelKey(),
), LibraryRouter {

    @Composable
    override fun Content() {
        LibraryScreen(
            viewModel = decomposeViewModel(),
            router = this
        )
    }

    override fun addProject(projectId: Long?) {
        onAddProject.invoke(projectId)
    }

    override fun editProject(projectId: Long) {

    }

    override fun runProject(projectId: Long) {
        onRunProject.invoke(projectId)
    }
}