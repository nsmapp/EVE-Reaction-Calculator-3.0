package be.nepravsky.sm.evereactioncalculator.projectbuilder

import androidx.compose.runtime.Composable
import be.nepravsky.sm.evereactioncalculator.navigation.Rout
import be.nepravsky.sm.evereactioncalculator.viewmodel.viewModelKey
import be.belveb.builder.BuilderRouter
import be.belveb.builder.BuilderScreen
import be.belveb.builder.BuilderViewModel
import com.arkivanov.decompose.ComponentContext

class ProjectBuilderRouterImpl(
    componentContext: ComponentContext,
    private val onOpenSearchSettings: () -> Unit,
) : Rout(componentContext, BuilderViewModel::class.viewModelKey()), BuilderRouter {


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
}