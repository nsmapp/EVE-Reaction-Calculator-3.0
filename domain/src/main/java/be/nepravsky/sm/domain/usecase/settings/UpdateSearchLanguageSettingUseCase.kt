package be.nepravsky.sm.domain.usecase.settings

import be.nepravsky.sm.domain.repo.SettingRepo
import be.nepravsky.sm.domain.utils.DispatcherProvider
import kotlinx.coroutines.withContext
import org.koin.core.annotation.Factory

@Factory
class UpdateSearchLanguageSettingUseCase(
    private val settingsRepo: SettingRepo,
    private val dispatcherProvider: DispatcherProvider,
) {

    suspend fun invoke(languageId: Long): Unit {
        withContext(dispatcherProvider.io) {
            runCatching {
                settingsRepo.updateSearchLanguage(languageId)
            }
        }
    }
}