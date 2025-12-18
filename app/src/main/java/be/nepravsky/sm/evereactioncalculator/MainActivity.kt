package be.nepravsky.sm.evereactioncalculator

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import be.nepravsky.sm.uikit.theme.AppTheme
import org.koin.android.ext.android.getKoin
import org.koin.core.module.Module

class MainActivity : ComponentActivity() {

//    private lateinit var firebaseAnalytics: FirebaseAnalytics

    private var defaultComponentContextModules: List<Module>? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

//        firebaseAnalytics = FirebaseAnalytics.getInstance(this)

        setContent {
            AppTheme{
                EveReactionRout()
            }
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        defaultComponentContextModules?.let { getKoin().unloadModules(it) }
        defaultComponentContextModules = null
    }
}
