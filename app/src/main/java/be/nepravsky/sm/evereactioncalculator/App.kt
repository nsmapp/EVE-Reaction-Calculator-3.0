package be.nepravsky.sm.evereactioncalculator

import android.app.Application
import androidx.compose.runtime.Composable
import be.nepravsky.sm.uikit.theme.AppTheme


class App: Application(){

    override fun onCreate() {
        super.onCreate()
        KoinDiHolder.getInstance(this@App)
    }
}


@Composable
fun App(initialDepsHolder: InitialDepsHolder){

    AppTheme(){
        initialDepsHolder.rootNavGraph.Content()
    }

}