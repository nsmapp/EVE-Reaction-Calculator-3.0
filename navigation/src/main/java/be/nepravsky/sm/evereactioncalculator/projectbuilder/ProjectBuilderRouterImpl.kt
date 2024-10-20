package be.nepravsky.sm.evereactioncalculator.projectbuilder

import androidx.compose.runtime.Composable
import androidx.lifecycle.ViewModelProvider
import be.nepravsky.sm.evereactioncalculator.navigation.Rout
import be.nepravsky.sm.evereactioncalculator.viewmodel.viewModelKey
import be.belveb.builder.BuilderRouter
import be.belveb.builder.BuilderScreen
import be.belveb.builder.BuilderViewModel
import be.nepravsky.sm.evereactioncalculator.viewmodel.KoinAssistedViewModelFactory
import com.arkivanov.decompose.ComponentContext
import org.koin.core.parameter.parametersOf

class ProjectBuilderRouterImpl(
    componentContext: ComponentContext,
    private val projectId: Long?,
    private val onOpenSearchSettings: () -> Unit,
    private val onBackPressed: () -> Unit,
) : Rout(componentContext, BuilderViewModel::class.viewModelKey()), BuilderRouter {


    override val viewModelFactory: ViewModelProvider.Factory by lazy {
        KoinAssistedViewModelFactory(
            parametersOf(projectId)
        )
    }

    @Composable
    override fun Content() {
        BuilderScreen(
            decomposeViewModel(),
            this,
        )
    }

    override fun openSearchSettings() {
        onOpenSearchSettings.invoke()
    }

    override fun onBackPressed() {
        onBackPressed.invoke()
    }
}