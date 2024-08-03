package be.nepravsky.sm.evereactioncalculator.contract


interface ReactionsContract {

    fun getBpcList(query: String)

    fun getActiveReactionGroupIds()

    fun openFilter()
}