package be.belveb.builder.model

import java.util.Date

data class ProjectBuilderState(
    val id: Long?,
    val name: String,
    val items: MutableList<ProjectItemModel>,
    val isShowTypeBottomSheet: Boolean,
    val types: List<BpcShortModel>

){
    companion object{

        val EMPTY = ProjectBuilderState(
            id = null,
            name = Date().time.toString(),
            items = mutableListOf(),
            isShowTypeBottomSheet = false,
            types = emptyList()
        )
    }
}
