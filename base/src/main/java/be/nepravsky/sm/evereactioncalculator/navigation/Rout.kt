package be.nepravsky.sm.evereactioncalculator.navigation

import androidx.compose.runtime.Composable
import androidx.lifecycle.ViewModelProvider
import be.nepravsky.sm.evereactioncalculator.viewmodel.BaseViewModel
import be.nepravsky.sm.evereactioncalculator.viewmodel.KoinViewModelFactory
import be.nepravsky.sm.evereactioncalculator.viewmodel.ViewModelInstanceKeeper
import com.arkivanov.decompose.ComponentContext
import com.arkivanov.essenty.instancekeeper.getOrCreate
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject

abstract class Rout(
    componentContext: ComponentContext,
    private val viewModelKey: String,
):KoinComponent, ComponentContext by componentContext {

    private val  defViewModelFactory: KoinViewModelFactory by inject()

    open val viewModelFactory: ViewModelProvider.Factory by lazy {
        defViewModelFactory
    }

    val viewModelKeeper: ViewModelInstanceKeeper by lazy {
        instanceKeeper.get(viewModelKey) as ViewModelInstanceKeeper
    }

    var viewModalInstance: BaseViewModel? = null

    init {
        keepViewModelInstance()
    }

    inline fun <reified T: BaseViewModel> decomposeViewModel(): T =
        viewModalInstance as? T ?: run {
            viewModalInstance = viewModelKeeper.getViewModel<T>()
            requireNotNull(viewModalInstance as T)
        }

    private fun keepViewModelInstance(){
        instanceKeeper.getOrCreate(viewModelKey){
            ViewModelInstanceKeeper{viewModelFactory}
        }
    }

    @Composable
    abstract fun Content()
}