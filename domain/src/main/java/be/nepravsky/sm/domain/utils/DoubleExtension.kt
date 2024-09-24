package be.nepravsky.sm.domain.utils

import java.text.DecimalFormat

fun Double.toISK(): String  = "${DecimalFormat("###,##0.00#").format(this)} ISK"

fun Double.toVolume(): String = "${DecimalFormat("###,##0.0#").format(this)} m³"
