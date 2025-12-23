package be.nepravsky.sm.evereactioncalculator

import android.content.Context

interface ReactorRouter {

    fun shareReaction(context: Context, text: String)
}