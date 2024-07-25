package be.nepravsky.sm.evereactioncalculator.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewmodel.CreationExtras
import org.koin.core.component.KoinComponent
import org.koin.core.parameter.ParametersHolder
import kotlin.reflect.KClass

class KoinAssistedViewModelFactory(
    private val args: ParametersHolder
):ViewModelProvider.Factory, KoinComponent {

    override fun <T : ViewModel> create(
        modelClass: KClass<T>,
        extras: CreationExtras,
    ): T = getKoin().get(modelClass){ args }

}