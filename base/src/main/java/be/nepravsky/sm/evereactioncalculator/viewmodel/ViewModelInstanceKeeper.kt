package be.nepravsky.sm.evereactioncalculator.viewmodel

import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewmodel.CreationExtras
import com.arkivanov.essenty.instancekeeper.InstanceKeeper
import kotlinx.coroutines.sync.Mutex
import kotlin.reflect.KClass

class ViewModelInstanceKeeper(
    private val viewModelFProvider: () -> ViewModelProvider.Factory,
): InstanceKeeper.Instance {

    private val mutex = Mutex()
    private var instance: BaseViewModel? = null

    inline fun <reified T: BaseViewModel> getViewModel(): T = getViewModel(T::class)

    fun <T: BaseViewModel> getViewModel(clazz: KClass<T>): T{
        if ( instance != null) return instance as T
        instance = viewModelFProvider.invoke().create(clazz, CreationExtras.Empty)
        return  instance as T
    }

    override fun onDestroy() {
        instance?.onClear()
        instance = null
    }
}