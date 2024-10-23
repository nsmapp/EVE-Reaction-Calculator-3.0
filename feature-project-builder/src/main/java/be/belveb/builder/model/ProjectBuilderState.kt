package be.belveb.builder.model

import be.nepravsky.sm.domain.utils.TEXT_EMPTY

data class ProjectBuilderState(
    val id: Long?,
    val name: String,
    val items: List<ProjectItemModel>,
    val isShowTypeBottomSheet: Boolean,
    val types: List<BpcShortModel>

){
    companion object{

        val EMPTY = ProjectBuilderState(
            id = null,
            name = TEXT_EMPTY,
            items = listOf(),
            isShowTypeBottomSheet = false,
            types = emptyList()
        )
    }
}
