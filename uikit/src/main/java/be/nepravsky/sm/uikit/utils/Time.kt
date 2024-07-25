package be.nepravsky.sm.uikit.utils

import be.nepravsky.sm.uikit.utils.model.ReactionTime


fun Long.toTime(): ReactionTime {

    val sec = this % 60
    val min = (this - sec) / 60  % 60
    val hour = (this - min * 60 - sec) / 60 / 60 % 24
    val day = (this - sec - min * 60 - hour * 24) / 60 / 60 / 24

    return ReactionTime(
        day = day, hour = hour, min = min, sec = sec
    )
}