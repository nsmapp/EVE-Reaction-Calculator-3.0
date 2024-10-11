package be.nepravsky.sm.domain.usecase.settings

import be.nepravsky.sm.domain.model.query.PriceLocationQuery
import be.nepravsky.sm.domain.repo.SettingRepo
import be.nepravsky.sm.domain.utils.DispatcherProvider
import kotlinx.coroutines.withContext
import org.koin.core.annotation.Factory

@Factory
class UpdatePriceLocationSettingUseCase(
    private val settingsRepo: SettingRepo,
    private val dispatcherProvider: DispatcherProvider,
) {

    suspend fun invoke(query: PriceLocationQuery): Unit {
        withContext(dispatcherProvider.io) {
            runCatching {
                settingsRepo.updatePriceLocationSettings(query)
            }
        }
    }
}