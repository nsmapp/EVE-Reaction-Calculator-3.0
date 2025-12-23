package be.nepravsky.sm.evereactioncalculator.reactor

import android.content.Context
import android.content.Intent
import be.nepravsky.sm.evereactioncalculator.ReactorRouter

class ReactorRouterImpl: ReactorRouter {

    override fun shareReaction(context: Context, text: String) {
        val sendIntent = Intent(Intent.ACTION_SEND).apply {
            putExtra(Intent.EXTRA_TEXT, text)
            type = "text/plain"
        }
        val shareIntent = Intent.createChooser(sendIntent, null)
        context.startActivity(shareIntent,null)
    }
}