package be.nepravsky.sm.evereactioncalculator

import androidx.lifecycle.viewModelScope
import be.nepravsky.sm.domain.model.settings.Settings
import be.nepravsky.sm.domain.usecase.settings.GetSettingsUseCase
import be.nepravsky.sm.domain.usecase.settings.UpdateIgnoreFuelBlockSettingUseCase
import be.nepravsky.sm.domain.usecase.settings.UpdateOfflineModeSettingUseCase
import be.nepravsky.sm.evereactioncalculator.model.SettingsState
import be.nepravsky.sm.evereactioncalculator.viewmodel.BaseViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import org.koin.core.annotation.Factory

@Factory(binds = [BaseViewModel::class])
class SettingsViewModel(
    private val getSettingsUseCase: GetSettingsUseCase,
    private val updateOfflineModeSettingUseCase: UpdateOfflineModeSettingUseCase,
    private val updateIgnoreFuelBlockSettingUseCase: UpdateIgnoreFuelBlockSettingUseCase,
) : BaseViewModel() {


    private val _state: MutableStateFlow<SettingsState> = MutableStateFlow(SettingsState.EMPTY)
    val state = _state.asStateFlow()

    init {

        viewModelScope.launch {
            getSettingsUseCase.invoke()
                .collectLatest { settings ->
                    handleSettings(settings)
                }
        }

    }

    private fun handleSettings(settings: Settings) {
        _state.update {
            SettingsState(
                id = settings.id,
                langId = settings.langId,
                systemId = settings.systemId,
                regionId = settings.regionId,
                isOfflineMode = settings.isOfflineMode,
                isIgnoreFuelBlock = settings.isIgnoreFuelBlock,
                isProgress = false,
            )
        }
    }

    fun setOfflineMode(checked: Boolean) {
        viewModelScope.launch {
            updateOfflineModeSettingUseCase.invoke(checked)
        }
    }

    fun setIsIgnoreFuelBlockBpc(checked: Boolean) {
        viewModelScope.launch {
            updateIgnoreFuelBlockSettingUseCase.invoke(checked)
        }

    }

}