package be.belveb.builder.model

import java.util.Date

data class ProjectBuilderState(
    val id: Long?,
    val name: String,
    val items: List<ProjectItemModel>

){
    companion object{

        val EMPTY = ProjectBuilderState(
            id = null,
            name = Date().time.toString(),
            items = emptyList()
        )
    }
}
