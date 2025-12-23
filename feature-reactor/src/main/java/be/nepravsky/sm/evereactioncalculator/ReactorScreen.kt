package be.nepravsky.sm.evereactioncalculator

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.BorderStroke
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
import androidx.compose.foundation.pager.PagerState
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
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalFocusManager
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
import org.koin.androidx.compose.koinViewModel
import org.koin.core.parameter.parametersOf
import be.nepravsky.sm.evereactioncalculator.model.ReactorState


@Composable
fun ReactorScreen(
    reactionId: Long,
    isSingleReaction: Boolean,
    router: ReactorRouter,
) {
    val viewModel = koinViewModel<ReactorViewModel>(
        key = ReactorViewModel::class.simpleName + reactionId,
        parameters = { parametersOf(reactionId, isSingleReaction) }
    )

    val focusManager = LocalFocusManager.current
    val context = LocalContext.current
    val snackBarHostState = remember { SnackbarHostState() }
    val coroutineScope = rememberCoroutineScope()

    val state by viewModel.state.collectAsState()

    val pagerState = rememberPagerState(pageCount = { ReactionTab.entries.size })
    val selectedTabIndex = pagerState.currentPage

    LaunchedEffect(Unit) {
        focusManager.clearFocus()
        viewModel.sideEffect.collect { effect ->
            when (effect) {
                is ReactorSideEffect.ShareReaction -> router.shareReaction(context, effect.text)
                is ReactorSideEffect.PriceUpdateError -> {
                    coroutineScope.launch {
                        snackBarHostState.showSnackbar(
                            message = context.getString(R.string.feature_reactor_failed_to_update_some_prices),
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

    if (state.isShowShareDialog) ShareReactionDialog(
        onDismiss = { viewModel.hideShareDialog() },
        onSimpleTextShare = { viewModel.shareAsSimpleText(selectedTabIndex.isBaseType()) },
        onRichTextShare = { viewModel.shareAsEveNoteText(selectedTabIndex.isBaseType()) },
    )

    ReactorScreenContent(
        state = state,
        selectedTabIndex = selectedTabIndex,
        pagerState = pagerState,
        snackBarHostState = snackBarHostState,
        onChangeReactionInformationVisibility =
            remember(viewModel) { viewModel::changeReactionInformationVisibility },
        onShowShareDialog = remember(viewModel) { viewModel::showShareDialog },
        onSetRuns = remember(viewModel) { viewModel::setRuns },
        onSetMe = remember(viewModel) { viewModel::setMe },
        onSetSubMe = remember(viewModel) { viewModel::setSubMe },
        onDisableOfflineMode = remember(viewModel) { viewModel::disableOfflineMode },
    )
}

@Composable
private fun ReactorScreenContent(
    state: ReactorState,
    selectedTabIndex: Int,
    pagerState: PagerState,
    snackBarHostState: SnackbarHostState,
    onChangeReactionInformationVisibility: () -> Unit = {},
    onShowShareDialog: () -> Unit = {},
    onSetRuns: (run: Long) -> Unit = {},
    onSetMe: (me: Double) -> Unit = {},
    onSetSubMe: (subMe: Double) -> Unit = {},
    onDisableOfflineMode: () -> Unit = {},
) {
    FullScreenProgressBox(
        isShowProgress = state.isShowProgress
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
                            .clickable { onChangeReactionInformationVisibility() },
                        imageVector = if (state.isShowReactionInformation)
                            Icons.Default.MoreVert else Icons.Default.Menu,
                        colorFilter = ColorFilter.tint(AppTheme.colors.accent)
                    )

                    SmallIcon(
                        modifier = Modifier
                            .padding(AppTheme.padding.s_2)
                            .clickable { onShowShareDialog() },
                        imageVector = Icons.Default.Share,
                        colorFilter = ColorFilter.tint(AppTheme.colors.accent)
                    )
                }

                AnimatedVisibility(
                    modifier = Modifier.padding(AppTheme.padding.s_4),
                    visible = state.isShowReactionInformation,
                ) {
                    ReactionInformationView(state, selectedTabIndex)
                }

                AnimatedVisibility(
                    visible = state.isSingleReaction
                ) {
                    ReactionControlView(
                        run = state.run,
                        me = state.me,
                        subMe = state.subMe,
                        isMeEnabled = state.isMeEnabled,
                        onRunChanged = onSetRuns,
                        onMeChanged = onSetMe,
                        onSubMeChanged = onSetSubMe,
                    )
                }
            }

            ReactionPageView(
                selectedTabIndex = selectedTabIndex,
                pagerState = pagerState,
                hasZeroPrice = state.data.hasZeroPrice,
                items = state.data.items,
                baseItems = state.data.baseItems,
                gradient1 = rightLeftGradient,
                gradient2 = leftRightGradient
            )

            if (state.isOfflineMode) OfflineModeInformationView(
                onDisableOfflineMode = onDisableOfflineMode
            )
        }

        //TODO custom snack bar
        SnackbarHost(
            hostState = snackBarHostState,
            modifier = Modifier.align(Alignment.BottomCenter)
        )
    }
}


private fun Int.isBaseType(): Boolean = this == ReactionTab.BASE_TYPE.ordinal