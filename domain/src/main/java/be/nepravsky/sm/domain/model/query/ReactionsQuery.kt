package be.nepravsky.sm.domain.model.query

data class ReactionsQuery(
    val name: String,
    val groupIds: List<Long>,
)