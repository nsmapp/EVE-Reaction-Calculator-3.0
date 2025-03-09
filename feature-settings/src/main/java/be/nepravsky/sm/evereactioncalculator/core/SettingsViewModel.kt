package be.nepravsky.sm.evereactioncalculator.core

import androidx.lifecycle.viewModelScope
import be.nepravsky.sm.domain.model.query.PriceLocationQuery
import be.nepravsky.sm.domain.model.settings.Settings
import be.nepravsky.sm.domain.usecase.settings.GetSettingsUseCase
import be.nepravsky.sm.domain.usecase.settings.UpdateIgnoreFuelBlockSettingUseCase
import be.nepravsky.sm.domain.usecase.settings.UpdateOfflineModeSettingUseCase
import be.nepravsky.sm.domain.usecase.settings.UpdatePriceLocationSettingUseCase
import be.nepravsky.sm.domain.usecase.settings.UpdateSearchLanguageSettingUseCase
import be.nepravsky.sm.evereactioncalculator.core.mapper.SettingsStateMapper
import be.nepravsky.sm.evereactioncalculator.core.model.SettingsState
import be.nepravsky.sm.evereactioncalculator.core.model.SystemModel
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
    private val updateSearchLanguageSettingUseCase: UpdateSearchLanguageSettingUseCase,
    private val updatePriceLocationSettingUseCase: UpdatePriceLocationSettingUseCase,
    private val settingsStateMapper: SettingsStateMapper,
) : BaseViewModel(), SettingsContract {


    private val _state: MutableStateFlow<SettingsState> = MutableStateFlow(SettingsState.EMPTY)
    val state = _state.asStateFlow()

    init {

        viewModelScope.launch {
            getSettingsUseCase.invoke()
                .collectLatest { settings -> handleSettings(settings) }
        }

    }

    override fun handleSettings(settings: Settings) {
        _state.update { settingsStateMapper.map(settings) }
    }

    override fun setOfflineMode(checked: Boolean) {
        viewModelScope.launch { updateOfflineModeSettingUseCase.invoke(checked) }
    }

    override fun setIsIgnoreFuelBlockBpc(checked: Boolean) {
        viewModelScope.launch { updateIgnoreFuelBlockSettingUseCase.invoke(checked) }
    }

    override fun hideDialogs() {
        _state.update {
            it.copy(
                isShowLanguageDialog = false,
                isShowPriceLocationDialog = false,
            )
        }
    }

    override fun showSearchLanguageDialog() {
        _state.update { it.copy(isShowLanguageDialog = true) }
    }

    override fun showPriceLocationDialog() {
        _state.update { it.copy(isShowPriceLocationDialog = true) }
    }

    override fun setPriceLocation(systemModel: SystemModel) {
        hideDialogs()
        val query = PriceLocationQuery(
            systemId = systemModel.systemId,
            reactionId = systemModel.regionId,
        )

        viewModelScope.launch { updatePriceLocationSettingUseCase.invoke(query) }
    }

    override fun setSearchLanguage(languageId: Long) {
        hideDialogs()
        viewModelScope.launch { updateSearchLanguageSettingUseCase.invoke(languageId) }
    }

}