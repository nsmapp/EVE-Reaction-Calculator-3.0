package be.nepravsky.builder.model

import androidx.compose.runtime.Stable

@Stable
data class ProjectItemModel(
    val id: Long,
    val name: String,
    val runCount: String,
    val me: Double,
    val subMe: Double,
)
