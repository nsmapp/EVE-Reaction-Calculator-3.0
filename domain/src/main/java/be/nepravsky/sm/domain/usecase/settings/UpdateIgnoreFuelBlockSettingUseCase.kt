package be.nepravsky.sm.domain.usecase.settings

import be.nepravsky.sm.domain.repo.SettingRepo
import be.nepravsky.sm.domain.utils.DispatcherProvider
import kotlinx.coroutines.withContext
import org.koin.core.annotation.Factory

@Factory
class UpdateIgnoreFuelBlockSettingUseCase(
    private val settingsRepo: SettingRepo,
    private val dispatcherProvider: DispatcherProvider,
) {

    suspend fun invoke(isIgnoreFuelBlock: Boolean): Unit {
        withContext(dispatcherProvider.io) {
            runCatching {
                if (isIgnoreFuelBlock) settingsRepo.enableIgnoreFuelBlockBpc()
                else settingsRepo.disableIgnoreFuelBlockBpc()
            }
        }
    }
}