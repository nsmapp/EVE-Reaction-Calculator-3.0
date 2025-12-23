package be.nepravsky.builder.model

import androidx.compose.runtime.Stable


@Stable
data class BpcShortModel(
    val id: Long,
    val groupId: Long,
    val name: String,
    val baseTime: String,
    val runLimit: Long,
)
