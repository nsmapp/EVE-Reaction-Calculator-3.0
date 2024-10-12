package be.nepravsky.sm.domain.usecase.settings

import be.nepravsky.sm.domain.model.settings.PriceSource
import be.nepravsky.sm.domain.repo.SettingRepo
import be.nepravsky.sm.domain.utils.DispatcherProvider
import kotlinx.coroutines.withContext
import org.koin.core.annotation.Factory


@Factory
class GetPriceSourceUseCase(
    private val settingRepo: SettingRepo,
    private val dispatcherProvider: DispatcherProvider,
) {


    suspend fun invoke(): Result<PriceSource> =
        withContext(dispatcherProvider.io){
            runCatching {
                settingRepo.getPriceSource()
            }
        }
}