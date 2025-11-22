package be.nepravsky.sm.domain.model

data class CompleteReactionShort(
    val products: List<ReactionItem>,
    val materials: List<ReactionItem>,
    val typeIdSet: Set<Long>,
    val isFormula: Boolean,
)