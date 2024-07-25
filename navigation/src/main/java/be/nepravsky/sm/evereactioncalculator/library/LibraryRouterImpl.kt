package be.nepravsky.sm.evereactioncalculator.library

import androidx.compose.runtime.Composable
import be.nepravsky.sm.evereactioncalculator.LibraryRouter
import be.nepravsky.sm.evereactioncalculator.LibraryScreen
import be.nepravsky.sm.evereactioncalculator.LibraryViewModel
import be.nepravsky.sm.evereactioncalculator.navigation.Rout
import be.nepravsky.sm.evereactioncalculator.viewmodel.viewModelKey
import com.arkivanov.decompose.ComponentContext

class LibraryRouterImpl(
    componentContext: ComponentContext
): Rout(
    componentContext = componentContext,
    viewModelKey = LibraryViewModel::class.viewModelKey(),
), LibraryRouter {

    @Composable
    override fun Content() {
        LibraryScreen()
    }
}