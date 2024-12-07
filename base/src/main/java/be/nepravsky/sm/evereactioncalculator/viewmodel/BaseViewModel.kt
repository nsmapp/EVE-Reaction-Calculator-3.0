package be.nepravsky.sm.evereactioncalculator.viewmodel

import androidx.annotation.CallSuper
import androidx.lifecycle.ViewModel
import kotlin.reflect.KClass

open class BaseViewModel: ViewModel() {

    @CallSuper
    open fun onClear(){
        onCleared()
    }
}


const val VIEW_MODEL_PREFIX = "androidx.lifecycle.ViewModelProvider.DefaultKey"
fun <T: Any> KClass<T>.viewModelKey(): String = "$VIEW_MODEL_PREFIX:${this.qualifiedName}"
