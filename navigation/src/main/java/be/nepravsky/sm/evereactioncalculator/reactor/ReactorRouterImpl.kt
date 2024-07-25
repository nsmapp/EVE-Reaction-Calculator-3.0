package be.nepravsky.sm.evereactioncalculator.reactor

import androidx.compose.runtime.Composable
import be.nepravsky.sm.evereactioncalculator.LibraryViewModel
import be.nepravsky.sm.evereactioncalculator.ReactorRouter
import be.nepravsky.sm.evereactioncalculator.ReactorScreen
import be.nepravsky.sm.evereactioncalculator.ReactorViewModel
import be.nepravsky.sm.evereactioncalculator.navigation.Rout
import be.nepravsky.sm.evereactioncalculator.viewmodel.viewModelKey
import com.arkivanov.decompose.ComponentContext
import kotlinx.serialization.Serializable

class ReactorRouterImpl(componentContext: ComponentContext): Rout(
    componentContext = componentContext,
    viewModelKey = ReactorViewModel::class.viewModelKey(),
), ReactorRouter {






    @Composable
    override fun Content() {
        ReactorScreen()
    }

    sealed interface ReactorChild{

    }

    @Serializable
    sealed interface ReactorDestination{

    }
}