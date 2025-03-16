package be.nepravsky.sm.evereactioncalculator

import android.content.Intent
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.material.icons.filled.Share
import androidx.compose.material3.SnackbarDuration
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalFocusManager
import androidx.core.content.ContextCompat.startActivity
import be.nepravsky.sm.evereactioncalculator.model.ReactionTab
import be.nepravsky.sm.evereactioncalculator.model.ReactorSideEffect
import be.nepravsky.sm.evereactioncalculator.uikit.R
import be.nepravsky.sm.evereactioncalculator.view.OfflineModeInformationView
import be.nepravsky.sm.evereactioncalculator.view.ReactionControlView
import be.nepravsky.sm.evereactioncalculator.view.ReactionInformationView
import be.nepravsky.sm.evereactioncalculator.view.ReactionPageView
import be.nepravsky.sm.evereactioncalculator.view.ShareReactionDialog
import be.nepravsky.sm.uikit.theme.AppTheme
import be.nepravsky.sm.uikit.theme.colors.rightLeftGradient
import be.nepravsky.sm.uikit.theme.colors.leftRightGradient
import be.nepravsky.sm.uikit.view.FullScreenProgressBox
import be.nepravsky.sm.uikit.view.icons.SmallIcon
import kotlinx.coroutines.launch


@Composable
fun ReactorScreen(
    viewModel: ReactorViewModel,
    router: ReactorRouter,
) {
    val focusManager = LocalFocusManager.current
    val context = LocalContext.current
    val snackbarHostState = remember { SnackbarHostState() }
    val coroutineScope = rememberCoroutineScope()

    val state = viewModel.state.collectAsState()

    val pagerState = rememberPagerState(pageCount = { ReactionTab.entries.size })
    val selectedTabIndex = pagerState.currentPage

    LaunchedEffect(null) {
        focusManager.clearFocus()
        viewModel.sideEffect.collect { effect ->
            when (effect) {
                is ReactorSideEffect.ShareReaction -> {
                    val sendIntent = Intent(Intent.ACTION_SEND).apply {
                        putExtra(Intent.EXTRA_TEXT, effect.text)
                        type = "text/plain"
                    }
                    val shareIntent = Intent.createChooser(sendIntent, null)
                    context.startActivity(shareIntent,null)
                }

                is ReactorSideEffect.PriceUpdateError -> {
                    coroutineScope.launch {
                        snackbarHostState.showSnackbar(
                            context.getString(R.string.feature_reactor_failed_to_update_some_prices),
                            duration = SnackbarDuration.Short
                        )

                    }
                }

                else -> {
                    //do nothing
                }
            }

        }
    }


    if (state.value.isShowShareDialog) ShareReactionDialog(
        onDismiss = { viewModel.hideShareDialog() },
        onSimpleTextShare = { viewModel.shareAsSimpleText(selectedTabIndex.isBaseType()) },
        onRichTextShare = { viewModel.shareAsEveNoteText(selectedTabIndex.isBaseType()) },
    )

    FullScreenProgressBox(
        isShowProgress = state.value.isShowProgress
    ) {
        Column(
            modifier = Modifier
                .statusBarsPadding()
                .background(AppTheme.colors.foreground)
        ) {

            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .wrapContentHeight()
                    .padding(AppTheme.padding.s_2)
                    .border(
                        BorderStroke(
                            AppTheme.viewSize.border_small,
                            AppTheme.colors.foreground_hard
                        ), RoundedCornerShape(AppTheme.radius.r_8)
                    )
                    .background(
                        AppTheme.colors.foreground_light,
                        RoundedCornerShape(AppTheme.radius.r_4)
                    )
            ) {

                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    //TODO add collapse icon
                    SmallIcon(
                        modifier = Modifier
                            .padding(AppTheme.padding.s_2)
                            .clickable { viewModel.changeReactionInformationVisibility() },
                        imageVector = if (state.value.isShowReactionInformation)
                            Icons.Default.MoreVert else Icons.Default.Menu,
                        colorFilter = ColorFilter.tint(AppTheme.colors.accent)
                    )

                    SmallIcon(
                        modifier = Modifier
                            .padding(AppTheme.padding.s_2)
                            .clickable {
                                viewModel.showShareDialog()
                            },
                        imageVector = Icons.Default.Share,
                        colorFilter = ColorFilter.tint(AppTheme.colors.accent)
                    )
                }

                AnimatedVisibility(
                    modifier = Modifier.padding(AppTheme.padding.s_4),
                    visible = state.value.isShowReactionInformation,
                ) {
                    ReactionInformationView(state, selectedTabIndex)
                }

                AnimatedVisibility(
                    visible = state.value.isSingleReaction
                ) {
                    ReactionControlView(
                        state = state.value,
                        onRunChanged = { run: Long -> viewModel.setRuns(run) },
                        onMeChanged = { me: Double -> viewModel.setMe(me) },
                        onSubMeChanged = { subMe: Double -> viewModel.setSubMe(subMe) },

                        )
                }
            }


            ReactionPageView(
                selectedTabIndex = selectedTabIndex,
                pagerState = pagerState,
                state = state,
                gradient1 = rightLeftGradient,
                gradient2 = leftRightGradient
            )

            if (state.value.isOfflineMode) OfflineModeInformationView(
                onDisableOfflineMode = { viewModel.disableOfflineMode() }
            )

        }

        //TODO custom snack bar
        SnackbarHost(
            hostState = snackbarHostState,
            modifier = Modifier.align(Alignment.BottomCenter)
        )
    }

}


private fun Int.isBaseType(): Boolean = this == ReactionTab.BASE_TYPE.ordinal