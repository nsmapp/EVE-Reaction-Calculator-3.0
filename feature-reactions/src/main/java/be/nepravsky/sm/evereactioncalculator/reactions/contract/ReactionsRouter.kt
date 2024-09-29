package be.nepravsky.sm.evereactioncalculator.reactions.contract

interface ReactionsRouter{

    fun openSearchSettings()

    fun buildReaction(id: Long, isSingleReaction: Boolean)
}