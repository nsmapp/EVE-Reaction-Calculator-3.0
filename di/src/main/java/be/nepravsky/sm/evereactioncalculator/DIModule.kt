package be.nepravsky.sm.evereactioncalculator

import be.nepravsky.sm.database.DatabaseModule
import be.nepravsky.sm.domain.DomainModule
import be.nepravsky.sm.evereactioncalculator.LibraryModule
import be.nepravsky.sm.evereactioncalculator.MainModule
import be.nepravsky.sm.evereactioncalculator.NavigationModule
import be.nepravsky.sm.evereactioncalculator.ReactionsModule
import be.nepravsky.sm.evereactioncalculator.ReactorModule
import be.nepravsky.sm.evereactioncalculator.SettingsModule
import org.koin.core.annotation.ComponentScan
import org.koin.core.annotation.Module

@Module(
    includes = [
        ViewModelModule::class,
        DomainModule::class, DatabaseModule::class, NavigationModule::class,
        LibraryModule::class, MainModule::class, ReactionsModule::class, ReactorModule::class,
        SettingsModule::class,
    ]
)
@ComponentScan
class DiModule{


}