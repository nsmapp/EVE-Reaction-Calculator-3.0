package be.nepravsky.searchsettings.model

import androidx.compose.runtime.Stable
import kotlinx.collections.immutable.*
import be.nepravsky.sm.domain.model.ReactionGroup

@Stable
data class SearchSettingsState(
    val reactionGroups: ImmutableList<ReactionGroup>
){
    companion object{
        val EMPTY = SearchSettingsState(
            reactionGroups = persistentListOf()
        )
    }
}