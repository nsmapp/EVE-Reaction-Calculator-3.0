package be.nepravsky.sm.domain.model.bpc

import be.nepravsky.sm.domain.model.ReactionItem

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