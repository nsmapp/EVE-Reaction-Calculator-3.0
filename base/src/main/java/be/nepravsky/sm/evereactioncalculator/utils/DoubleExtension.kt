package be.nepravsky.sm.evereactioncalculator.utils

import java.text.DecimalFormat

fun Double.toISK(): String =
    if (this <= 0.0) "-" else "${DecimalFormat("###,##0.00#").format(this)} ISK"

fun Double.toVolume(): String = "${DecimalFormat("###,##0.0#").format(this)} m³"
