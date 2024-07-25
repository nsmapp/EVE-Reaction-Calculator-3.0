package be.nepravsky.sm.evereactioncalculator

import androidx.compose.runtime.Composable
import com.arkivanov.decompose.router.stack.ChildStack
import com.arkivanov.decompose.value.Value

interface RootRouter{


    val stack: Value<ChildStack<*, RootChild>>

    @Composable
    fun Content()

    fun onBackClicked(toIndex: Int)




}