package be.nepravsky.sm.evereactioncalculator.model

import be.nepravsky.sm.domain.model.Blueprint

data class ReactionsState(

    val bpcList: List<Blueprint>
){
    companion object{
        val EMPTY = ReactionsState(
            bpcList = emptyList()
        )
    }
}