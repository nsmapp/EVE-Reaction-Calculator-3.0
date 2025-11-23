package be.nepravsky.sm.domain.model

import androidx.compose.runtime.Immutable

@Immutable
data class ReactionGroup(
    val id: Long,
    val isFormula: Boolean,
    val isSelected: Boolean,
    val category: Long,
    val name: String,
    val iconId: Long,
)