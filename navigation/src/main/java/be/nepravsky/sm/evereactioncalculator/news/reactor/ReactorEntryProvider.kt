package be.nepravsky.sm.evereactioncalculator.news.reactor

import android.content.Intent
import androidx.navigation3.runtime.EntryProviderScope
import androidx.navigation3.runtime.NavKey
import be.nepravsky.sm.evereactioncalculator.ReactorScreen
import be.nepravsky.sm.evereactioncalculator.news.Navigator

fun EntryProviderScope<NavKey>.reactor(navigator: Navigator){
    entry<ReactorDest.Reactor> { data ->
        ReactorScreen(
            reactionId = data.reactionId,
            isSingleReaction = data.isSingleReaction,
            onShareReaction = {context, string ->
                val sendIntent = Intent(Intent.ACTION_SEND).apply {
                    putExtra(Intent.EXTRA_TEXT, string)
                    type = "text/plain"
                }
                val shareIntent = Intent.createChooser(sendIntent, null)
                context.startActivity(shareIntent,null)
            }
        )
    }
}