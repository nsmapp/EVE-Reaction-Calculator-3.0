package be.nepravsky.sm.evereactioncalculator.news.about

import android.content.Intent
import androidx.core.net.toUri
import androidx.navigation3.runtime.EntryProviderScope
import androidx.navigation3.runtime.NavKey
import be.nepravsky.sm.evereactioncalculator.about.AboutScreen
import be.nepravsky.sm.evereactioncalculator.news.Navigator


fun EntryProviderScope<NavKey>.about(navigator: Navigator){

    entry<AboutDest.About> {
        AboutScreen(
            onNavigateBack = { navigator.navigateBack() },
            onOpenGitHubLink = { context, url: String ->
                try {
                    val intent = Intent(Intent.ACTION_VIEW, url.toUri())
                    context.startActivity(intent)
                } catch (e: Exception) {

                }
            },
            onSendEmail = {context, mail, subject ->

                try {
                    val intent = Intent(Intent.ACTION_SEND)
                    intent.type = "vnd.android.cursor.item/email"
                    intent.putExtra(Intent.EXTRA_EMAIL, arrayOf(mail))
                    intent.putExtra(Intent.EXTRA_SUBJECT, subject)
                    context.startActivity(intent)

                } catch (e: Exception) {
                }
            }
        )
    }
}