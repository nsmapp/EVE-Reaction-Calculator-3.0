package be.nepravsky.sm.evereactioncalculator.reactions.model

data class ReactionsState(

    val bpcShortList: List<BpcShortModel>
){
    companion object{
        val EMPTY = ReactionsState(
            bpcShortList = emptyList()
        )
    }
}