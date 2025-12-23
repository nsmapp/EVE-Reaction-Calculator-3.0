package be.nepravsky.sm.evereactioncalculator.reactions.contract


interface ReactionsContract {

    fun onScreeOpen()
    fun searchReactions(query: String)
}