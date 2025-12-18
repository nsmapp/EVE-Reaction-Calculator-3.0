package be.nepravsky.sm.evereactioncalculator

import be.nepravsky.sm.database.DatabaseModule
import be.nepravsky.sm.database.network.NetworkModule
import be.nepravsky.sm.domain.DomainModule
import be.nepravsky.builder.BuilderModule
import be.nepravsky.searchsettings.SearchSettingsModule
import be.nepravsky.sm.evereactioncalculator.library.LibraryModule
import be.nepravsky.sm.evereactioncalculator.mainscreen.MainModule
import org.koin.core.annotation.ComponentScan
import org.koin.core.annotation.Module

@Module(
    includes = [
        ViewModelModule::class,
        DomainModule::class, DatabaseModule::class,
        LibraryModule::class,
        MainModule::class,
        ReactionsModule::class, ReactorModule::class,
        SettingsModule::class, NetworkModule::class, BuilderModule::class,
        SearchSettingsModule::class,
    ]
)
@ComponentScan
class DiModule {


}