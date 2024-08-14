package be.nepravsky.sm.evereactioncalculator.reactor

import androidx.compose.runtime.Composable
import androidx.lifecycle.ViewModelProvider
import be.nepravsky.sm.evereactioncalculator.ReactorRouter
import be.nepravsky.sm.evereactioncalculator.ReactorScreen
import be.nepravsky.sm.evereactioncalculator.ReactorViewModel
import be.nepravsky.sm.evereactioncalculator.navigation.Rout
import be.nepravsky.sm.evereactioncalculator.viewmodel.KoinAssistedViewModelFactory
import be.nepravsky.sm.evereactioncalculator.viewmodel.viewModelKey
import com.arkivanov.decompose.ComponentContext
import kotlinx.serialization.Serializable
import org.koin.core.parameter.parametersOf

class ReactorRouterImpl(
    private val reactionId: Long,
    componentContext: ComponentContext
): Rout(
    componentContext = componentContext,
    viewModelKey = ReactorViewModel::class.viewModelKey(),
), ReactorRouter {


    override val viewModelFactory: ViewModelProvider.Factory by lazy {
        KoinAssistedViewModelFactory(
            parametersOf(reactionId)
        )
    }



    @Composable
    override fun Content() {
        ReactorScreen(
            viewModel = decomposeViewModel(),
            router = this,
        )
    }

    sealed interface ReactorChild{

    }

    @Serializable
    sealed interface ReactorDestination{

    }
}