package be.nepravsky.sm.evereactioncalculator.reactions.model

import androidx.compose.runtime.Stable
import be.nepravsky.sm.evereactioncalculator.utils.TEXT_EMPTY
import kotlinx.collections.immutable.ImmutableList
import kotlinx.collections.immutable.persistentListOf

@Stable
data class ReactionsState(
    val bpcShortList: ImmutableList<BpcShortModel>,
    val searchText: String = TEXT_EMPTY,

){
    companion object{
        val EMPTY = ReactionsState(
            bpcShortList = persistentListOf(),
            searchText = TEXT_EMPTY,
        )
    }
}