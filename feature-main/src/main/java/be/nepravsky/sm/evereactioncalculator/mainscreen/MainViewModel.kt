package be.nepravsky.sm.evereactioncalculator.mainscreen

import androidx.lifecycle.viewModelScope
import be.nepravsky.sm.evereactioncalculator.mainscreen.model.Tabs
import be.nepravsky.sm.evereactioncalculator.viewmodel.BaseViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import org.koin.core.annotation.Factory
import org.koin.core.annotation.InjectedParam

@Factory(binds = [BaseViewModel::class])
class MainViewModel(
    @InjectedParam private val initialTab: Tabs,
): BaseViewModel() {

    private val _activeTab = MutableStateFlow(initialTab)
    val activeTab = _activeTab.asStateFlow()

    private val _channel = Channel<Tabs>()
    val channel = _channel.receiveAsFlow().flowOn(Dispatchers.Main.immediate)

    fun setActiveTab(tab: Tabs){
        _activeTab.update { tab }
        viewModelScope.launch {
            _channel.send(tab)
        }
    }
}