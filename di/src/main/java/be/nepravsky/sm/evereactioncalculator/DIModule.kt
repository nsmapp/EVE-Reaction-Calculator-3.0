package be.nepravsky.sm.evereactioncalculator

import be.nepravsky.sm.database.DatabaseModule
import be.nepravsky.sm.database.network.NetworkModule
import be.nepravsky.sm.domain.DomainModule
import be.belveb.builder.BuilderModule
import be.belveb.searchsettings.SearchSettingsModule
import org.koin.core.annotation.ComponentScan
import org.koin.core.annotation.Module

@Module(
    includes = [
        ViewModelModule::class,
        DomainModule::class, DatabaseModule::class, NavigationModule::class,
        LibraryModule::class, MainModule::class, ReactionsModule::class, ReactorModule::class,
        SettingsModule::class, NetworkModule::class, BuilderModule::class,
        SearchSettingsModule::class,
    ]
)
@ComponentScan
class DiModule {


}