package be.nepravsky.sm.evereactioncalculator.about

import android.content.Context
import android.content.Intent
import androidx.core.net.toUri

class AboutRouterImpl(
    private val onNavigationBack: (() -> Unit)
) : AboutRouter {

    override fun navigateBack() {
        onNavigationBack()
    }

    override fun openGitHubLink(context: Context, url: String) {
        try {
            val intent = Intent(Intent.ACTION_VIEW, url.toUri())
            context.startActivity(intent)
        } catch (e: Exception) {

        }
    }

    override fun sendEmail(
        context: Context, mail: String, subject: String
    ) {
        try {
            val intent = Intent(Intent.ACTION_SEND)
            intent.type = "vnd.android.cursor.item/email"
            intent.putExtra(Intent.EXTRA_EMAIL, arrayOf(mail))
            intent.putExtra(Intent.EXTRA_SUBJECT, subject)
            context.startActivity(intent)

        } catch (e: Exception) {
        }
    }
}