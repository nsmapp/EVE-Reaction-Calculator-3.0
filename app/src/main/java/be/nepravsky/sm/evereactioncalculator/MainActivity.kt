package be.nepravsky.sm.evereactioncalculator

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import com.arkivanov.decompose.DefaultComponentContext
import com.arkivanov.decompose.defaultComponentContext
import com.google.firebase.analytics.FirebaseAnalytics
import org.koin.android.ext.android.getKoin
import org.koin.android.ext.android.inject
import org.koin.core.module.Module
import org.koin.dsl.module

class MainActivity : ComponentActivity() {

    private lateinit var firebaseAnalytics: FirebaseAnalytics

    private val initialDepsHolder: InitialDepsHolder by inject()
    private var defaultComponentContextModules: List<Module>? = null

    override fun onCreate(savedInstanceState: Bundle?) {

        defineDefaultComponentContext()
        super.onCreate(savedInstanceState)

        firebaseAnalytics = FirebaseAnalytics.getInstance(this)

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
