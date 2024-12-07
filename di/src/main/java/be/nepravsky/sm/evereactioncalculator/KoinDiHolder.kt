package be.nepravsky.sm.evereactioncalculator

import android.content.Context
import org.koin.core.Koin
import org.koin.core.KoinApplication
import org.koin.core.context.startKoin
import org.koin.core.module.Module
import org.koin.dsl.module
import org.koin.ksp.generated.module

class KoinDiHolder private constructor(di: KoinApplication){

    val koin: Koin by lazy { di.koin }

    companion object{
        private var instance: KoinDiHolder? = null

        fun getInstance(context: Context): KoinDiHolder = instance ?: kotlin.run {
            val koinApp = startKoin {
                modules(
                    module {single { context }},
                    DiModule().module, initialDepsModule, viewModels
                )
            }
            instance = KoinDiHolder(koinApp)
            instance!!
        }

    }
}

val initialDepsModule: Module = module {
    factory<InitialDepsHolder> { InitialDepsHolderAndroid() }
}