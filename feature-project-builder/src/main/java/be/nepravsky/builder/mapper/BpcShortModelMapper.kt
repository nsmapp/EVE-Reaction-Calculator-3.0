package be.nepravsky.builder.mapper


import be.nepravsky.builder.model.BpcShortModel
import be.nepravsky.sm.domain.model.bpc.BpcShort
import be.nepravsky.sm.evereactioncalculator.utils.TEXT_EMPTY
import org.koin.core.annotation.Factory

@Factory
class BpcShortModelMapper {


    fun map(bpcShort: BpcShort): BpcShortModel {


        val time = {
            val sec = bpcShort.baseTime % 60
            val min = (bpcShort.baseTime - sec) / 60 % 60
            val hour = (bpcShort.baseTime - min * 60 - sec) / 60 / 60 % 24
            val day = (bpcShort.baseTime - sec - min * 60 - hour * 24) / 60 / 60 / 24

            var time = TEXT_EMPTY
            if (day > 0) time += "${day}${Companion.DAY}"
            if (hour > 0) time += " ${hour}${Companion.HOUR}"
            if (min > 0) time += " ${min}${Companion.MIN}"
            if (sec > 0) time += " ${sec}${Companion.SEC}"

            time
        }

        return with(bpcShort) {
            BpcShortModel(
                id = id,
                groupId = groupId,
                name = name,
                baseTime = time.invoke(),
                runLimit = runLimit
            )
        }

    }

    companion object {
        private const val DAY = "d"
        private const val HOUR = "h"
        private const val MIN = "m"
        private const val SEC = "s"
    }
}