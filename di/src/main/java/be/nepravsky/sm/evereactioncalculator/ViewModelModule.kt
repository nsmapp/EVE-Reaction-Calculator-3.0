package be.nepravsky.sm.evereactioncalculator

import be.nepravsky.sm.evereactioncalculator.viewmodel.KoinViewModelFactory
import org.koin.core.annotation.Module
import org.koin.core.annotation.Single

@Module
class ViewModelModule {


    @Single
    fun provideKoinViewModelFactory() = KoinViewModelFactory()
}