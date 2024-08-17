package be.nepravsky.sm.evereactioncalculator

import be.nepravsky.sm.database.DatabaseModule
import be.nepravsky.sm.database.network.NetworkModule
import be.nepravsky.sm.domain.DomainModule
import org.koin.core.annotation.ComponentScan
import org.koin.core.annotation.Module

@Module(
    includes = [
        ViewModelModule::class,
        DomainModule::class, DatabaseModule::class, NavigationModule::class,
        LibraryModule::class, MainModule::class, ReactionsModule::class, ReactorModule::class,
        SettingsModule::class, NetworkModule::class,
    ]
)
@ComponentScan
class DiModule{


}