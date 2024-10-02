package be.nepravsky.sm.domain.usecase.settings

import be.nepravsky.sm.domain.model.settings.Settings
import be.nepravsky.sm.domain.repo.SettingRepo
import be.nepravsky.sm.domain.utils.DispatcherProvider
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOn
import org.koin.core.annotation.Factory

@Factory
class GetSettingsUseCase(
    private val settingRepo: SettingRepo,
    private val dispatcherProvider: DispatcherProvider,
) {

    fun invoke(): Flow<Settings> = settingRepo.getSettings()
        .flowOn(dispatcherProvider.io)
        .flowOn(dispatcherProvider.main)


}