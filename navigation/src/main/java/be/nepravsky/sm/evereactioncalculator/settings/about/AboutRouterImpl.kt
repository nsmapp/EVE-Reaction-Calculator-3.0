package be.nepravsky.sm.evereactioncalculator.settings.about

import android.content.Context
import android.content.Intent
import androidx.compose.runtime.Composable
import androidx.core.net.toUri
import be.nepravsky.sm.evereactioncalculator.about.AboutRouter
import be.nepravsky.sm.evereactioncalculator.about.AboutScreen
import be.nepravsky.sm.evereactioncalculator.about.AboutViewModel
import be.nepravsky.sm.evereactioncalculator.navigation.Rout
import be.nepravsky.sm.evereactioncalculator.viewmodel.viewModelKey
import com.arkivanov.decompose.ComponentContext

class AboutRouterImpl(
    componentContext: ComponentContext,
    private val onBackPressed: () -> Unit,
) : Rout(
    componentContext = componentContext,
    viewModelKey = AboutViewModel::class.viewModelKey()
), AboutRouter {


    @Composable
    override fun Content() {
        AboutScreen(
            decomposeViewModel(),
            this,
        )
    }

    override fun onFinish() {
        onBackPressed.invoke()
    }

    override fun openGitHubLink(context: Context, url: String) {
        try {
            val intent = Intent(Intent.ACTION_VIEW, url.toUri())
            context.startActivity(intent)
        }catch (e: Exception){

        }
    }

    override fun sendEmail(context: Context, mail: String, subject: String) {
        try {
            val intent = Intent(Intent.ACTION_SEND)
            intent.type = "vnd.android.cursor.item/email"
            intent.putExtra(Intent.EXTRA_EMAIL, arrayOf(mail))
            intent.putExtra(Intent.EXTRA_SUBJECT, subject)
            context.startActivity(intent)

        }catch (e: Exception){ }

    }
}