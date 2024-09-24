package be.nepravsky.sm.domain.model

data class BpcFull(
    val id: Long,
    val groupId: Long,
    val name: String,
    val baseTime: Long,
    val runLimit: Long,
    val materials: List<ReactionItem>,
    val products: List<ReactionItem>,
    val isFormula: Boolean,
)