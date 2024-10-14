package be.nepravsky.sm.evereactioncalculator.about

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.gestures.Orientation
import androidx.compose.foundation.gestures.scrollable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Info
import androidx.compose.material.icons.filled.MailOutline
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import be.nepravsky.sm.evereactioncalculator.settings.R
import be.nepravsky.sm.uikit.theme.AppTheme
import be.nepravsky.sm.uikit.view.appbar.CAppBar
import be.nepravsky.sm.uikit.view.icons.NormalIcon
import be.nepravsky.sm.uikit.view.text.TextMedium

@Composable
fun AboutScreen(
    viewModel: AboutViewModel,
    router: AboutRouter,
) {

    val context = LocalContext.current

    Column(
        modifier = Modifier
            .background(color = AppTheme.colors.foreground)
            .fillMaxSize(),
    ) {
        CAppBar(modifier = Modifier,
            text = stringResource(R.string.feature_settings_about),
            onBackPressed = { router.onFinish() })
        Column(
            modifier = Modifier
                .padding(AppTheme.padding.s_8)
                .scrollable(
                    state = rememberScrollState(), orientation = Orientation.Vertical
                ),
            verticalArrangement = Arrangement.SpaceBetween
        ) {
            //TODO add build version
            TextMedium(
                modifier = Modifier
                    .padding(AppTheme.padding.s_16)
                    .align(Alignment.CenterHorizontally),
                text = stringResource(R.string.feature_settings_eve_reaction_calculator_version)
            )

            TextMedium(
                modifier = Modifier
                    .padding(AppTheme.padding.s_8),
                text = stringResource(R.string.feature_settings_rights),
                style = AppTheme.typography.medium.copy(textAlign = TextAlign.Justify)
            )

            //TODO change mail and git hub icon
            Row(modifier = Modifier.align(Alignment.CenterHorizontally)) {

                NormalIcon(
                    modifier = Modifier
                        .padding(AppTheme.padding.s_8)
                        .clickable {
                            router.openGitHubLink(
                                context, context.getString(R.string.feature_settings_gitgab_link)
                            )
                        }, imageVector = Icons.Default.Info
                )
                NormalIcon(
                    modifier = Modifier
                        .padding(AppTheme.padding.s_8)
                        .clickable {
                            router.sendEmail(
                                context,
                                context.getString(R.string.feature_settings_nsmappinfo_gmail_com),
                                context.getString(
                                    R.string.featuew_settings_reaction_calculator
                                )
                            )
                        }, imageVector = Icons.Default.MailOutline
                )
            }
        }
    }
}