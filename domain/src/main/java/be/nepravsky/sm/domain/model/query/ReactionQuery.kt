package be.nepravsky.sm.domain.model.query

data class ReactionQuery(
    val bpcId: Long,
    val me: Double = 0.0,
    val subMe: Double = 0.0,
    val run: Long = 1,
)