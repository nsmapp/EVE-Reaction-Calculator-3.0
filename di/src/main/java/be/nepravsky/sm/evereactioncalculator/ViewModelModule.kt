package be.nepravsky.sm.evereactioncalculator

import be.nepravsky.builder.BuilderViewModel
import be.nepravsky.searchsettings.SearchSettingsViewModel
import be.nepravsky.sm.evereactioncalculator.about.AboutViewModel
import be.nepravsky.sm.evereactioncalculator.core.SettingsViewModel
import be.nepravsky.sm.evereactioncalculator.library.LibraryViewModel
import be.nepravsky.sm.evereactioncalculator.mainscreen.MainViewModel
import be.nepravsky.sm.evereactioncalculator.reactions.ReactionsViewModel
import be.nepravsky.sm.evereactioncalculator.viewmodel.BaseViewModel
import be.nepravsky.sm.evereactioncalculator.viewmodel.KoinViewModelFactory
import org.koin.core.annotation.Module
import org.koin.core.annotation.Single
import org.koin.dsl.bind
import org.koin.dsl.module

@Module
class ViewModelModule {


    @Single
    fun provideKoinViewModelFactory() = KoinViewModelFactory()
}

val viewModels = module {


    factory { LibraryViewModel(get(), get(), get()) } bind BaseViewModel::class

    factory { MainViewModel(get()) } bind BaseViewModel::class

    factory { AboutViewModel() } bind BaseViewModel::class

    factory {
        BuilderViewModel(
            projectId = get(),
            saveProjectUseCase = get(),
            projectBuildMapper = get(),
            getActiveGroupIdsUseCase = get(),
            getProjectUseCase = get(),
            getBpcListUseCase = get(),
            bpcShortModelMapper = get(),
        )
    } bind BaseViewModel::class

    factory { ReactionsViewModel(get(), get(), get()) } bind BaseViewModel::class

    factory {
        ReactorViewModel(
            reactionId = get(),
            isSingleReaction = get(),
            updatePriceUseCase = get(),
            makeReactionUseCase = get(),
            getPriceSourceUseCase = get(),
            updateOfflineModeSettingUseCase = get(),
            getProjectUseCase = get(),
            complexReactionMapper = get(),
            sharedReactionMapper = get(),
            projectMapper = get(),
        )
    } bind BaseViewModel::class

    factory { SearchSettingsViewModel(get(), get()) } bind BaseViewModel::class

    factory {
        SettingsViewModel(
            getSettingsUseCase = get(),
            updateOfflineModeSettingUseCase = get(),
            updateIgnoreFuelBlockSettingUseCase = get(),
            updateSearchLanguageSettingUseCase = get(),
            updatePriceLocationSettingUseCase = get(),
            settingsStateMapper = get(),
        )
    } bind BaseViewModel::class

    factory { p ->
        BuilderViewModel(
            projectId = p[0],
            saveProjectUseCase = get(),
            projectBuildMapper = get(),
            getActiveGroupIdsUseCase = get(),
            getProjectUseCase = get(),
            getBpcListUseCase = get(),
            bpcShortModelMapper = get()
        )
    } bind BaseViewModel::class
}