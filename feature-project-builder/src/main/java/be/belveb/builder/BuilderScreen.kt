package be.belveb.builder

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material.icons.filled.ThumbUp
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import be.belveb.builder.model.ProjectBuildSideEffect
import be.nepravsky.sm.uikit.theme.AppTheme
import be.nepravsky.sm.uikit.view.appbar.CAppBar
import be.nepravsky.sm.uikit.view.textfield.CTextField

@Composable
fun BuilderScreen(
    viewModel: BuilderViewModel,
    router: BuilderRouter
) {

    val state = viewModel.state.collectAsState()

    LaunchedEffect(null) {
        viewModel.sideEffect.collect{effect ->
            when(effect){
                is ProjectBuildSideEffect.CLOSE -> router.onBackPressed()
            }
        }
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(AppTheme.colors.foreground)
            .padding(horizontal = AppTheme.padding.s_8),
    ) {

        CAppBar(
            text = stringResource(be.nepravsky.sm.evereactioncalculator.builder.R.string.feeture_project_builder),
            onBackPressed = { router.onBackPressed() }
        )

        CTextField(
            modifier = Modifier
                .padding(bottom = AppTheme.padding.s_16)
                .fillMaxWidth(),
            value = state.value.name,
            onValueChange = { text ->
                viewModel.setProjectName(text)
            },
            //TODO replace icon
            trailingIcon = Icons.Default.Settings,
            onTrailingClick = { router.openSearchSettings() },
            leadingIcon = Icons.Default.ThumbUp,
            onLeadingClick = { viewModel.saveProject() },
            hint = stringResource(be.nepravsky.sm.evereactioncalculator.builder.R.string.feature_project_builder_project_name),
        )
    }
}