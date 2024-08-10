package be.nepravsky.sm.evereactioncalculator.reactions.model

import be.nepravsky.sm.domain.model.BpcShort

data class ReactionsState(

    val bpcShortList: List<BpcShort>
){
    companion object{
        val EMPTY = ReactionsState(
            bpcShortList = emptyList()
        )
    }
}