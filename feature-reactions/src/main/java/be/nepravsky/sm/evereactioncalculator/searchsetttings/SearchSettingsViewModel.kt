package be.nepravsky.sm.evereactioncalculator.searchsetttings

import androidx.lifecycle.viewModelScope
import be.nepravsky.sm.domain.model.query.ActiveGroupQuery
import be.nepravsky.sm.domain.usecase.groups.GetReactionGroupsUseCase
import be.nepravsky.sm.domain.usecase.groups.UpdateActiveGroupUseCase
import be.nepravsky.sm.evereactioncalculator.searchsetttings.contract.SearchSettingsContract
import be.nepravsky.sm.evereactioncalculator.searchsetttings.model.SearchSettingsState
import be.nepravsky.sm.evereactioncalculator.viewmodel.BaseViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import org.koin.core.annotation.Factory

@Factory(binds = [BaseViewModel::class])
class SearchSettingsViewModel(
    private val getReactionGroupsUseCase: GetReactionGroupsUseCase,
    private val updateActiveGroupUseCase: UpdateActiveGroupUseCase,
) : BaseViewModel(), SearchSettingsContract {

    private val _state = MutableStateFlow<SearchSettingsState>(SearchSettingsState.INIT)
    val state = _state.asStateFlow()


    override fun getReactionGroups() {
        viewModelScope.launch {
            getReactionGroupsUseCase.invoke()
                .onSuccess { groups -> _state.update { it.copy(reactionGroups = groups) } }
                .onFailure {
                    TODO("get reactions group error handle don't implemented: ${it.message}")
                }
        }
    }

    override fun onReactionGroupClick(groupId: Long, isSelected: Boolean) {
        _state.update {
            it.copy(
                reactionGroups = it.reactionGroups.map { group ->
                    group.copy(
                        isSelected = if (group.id == groupId) isSelected else group.isSelected
                    )
                }
            )
        }
        updateActiveReactions(ActiveGroupQuery(groupId, isSelected))
    }


    override fun updateActiveReactions(query: ActiveGroupQuery) {
        viewModelScope.launch {
            updateActiveGroupUseCase.invoke(query)
                .onSuccess {}
                .onFailure {
                    TODO("update active group don't implemented: ${it.message}")
                }

        }
    }
}