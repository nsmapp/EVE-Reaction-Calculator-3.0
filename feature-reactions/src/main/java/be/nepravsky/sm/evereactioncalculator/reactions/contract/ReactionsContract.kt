package be.nepravsky.sm.evereactioncalculator.reactions.contract


interface ReactionsContract {

    fun getBpcList(query: String)

    fun getActiveReactionGroupIds()

    fun openFilter()
}