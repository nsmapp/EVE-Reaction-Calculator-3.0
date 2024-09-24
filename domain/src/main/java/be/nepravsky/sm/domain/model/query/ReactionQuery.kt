package be.nepravsky.sm.domain.model.query

data class ReactionQuery(
    val bpcId: Long,
    val me: Double = 1.0,
    val subMe: Double = 1.0,
    val run: Long = 1,
)