package be.nepravsky.builder.model

import androidx.compose.runtime.Stable
import be.nepravsky.sm.domain.utils.TEXT_EMPTY
import kotlinx.collections.immutable.ImmutableList
import kotlinx.collections.immutable.persistentListOf

@Stable
data class ProjectBuilderState(
    val id: Long?,
    val name: String,
    val items: ImmutableList<ProjectItemModel>,
    val isShowTypeBottomSheet: Boolean,
    val types: ImmutableList<BpcShortModel>,
    val searchText: String = TEXT_EMPTY,

){
    companion object{

        val EMPTY = ProjectBuilderState(
            id = null,
            name = TEXT_EMPTY,
            items = persistentListOf(),
            isShowTypeBottomSheet = false,
            types = persistentListOf(),
            searchText = TEXT_EMPTY
        )
    }
}
