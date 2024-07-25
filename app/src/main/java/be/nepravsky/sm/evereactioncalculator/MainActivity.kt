package be.nepravsky.sm.evereactioncalculator

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.arkivanov.decompose.DefaultComponentContext
import com.arkivanov.decompose.defaultComponentContext
import org.koin.android.ext.android.getKoin
import org.koin.android.ext.android.inject
import org.koin.core.module.Module
import org.koin.dsl.module

class MainActivity : ComponentActivity() {


    private val initialDepsHolder: InitialDepsHolder by inject()
    private var defaultComponentContextModules: List<Module>? = null

    override fun onCreate(savedInstanceState: Bundle?) {

        defineDefaultComponentContext()
        super.onCreate(savedInstanceState)
        setContent {
           App(initialDepsHolder)
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        defaultComponentContextModules?.let { getKoin().unloadModules(it) }
        defaultComponentContextModules = null
    }

    private fun defineDefaultComponentContext() {
        val modulesNonNull = listOf(
            module { single<DefaultComponentContext> { defaultComponentContext() } }
        )

        defaultComponentContextModules = modulesNonNull

        getKoin().loadModules(
            modules = modulesNonNull,
            allowOverride = true
        )
    }
}
