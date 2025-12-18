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



