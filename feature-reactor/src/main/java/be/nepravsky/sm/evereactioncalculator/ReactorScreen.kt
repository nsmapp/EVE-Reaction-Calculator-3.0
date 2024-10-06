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
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.material.icons.filled.Share
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalFocusManager
import androidx.core.content.ContextCompat.startActivity
import be.nepravsky.sm.evereactioncalculator.model.ReactionTab
import be.nepravsky.sm.evereactioncalculator.model.ReactorSideEffect
import be.nepravsky.sm.evereactioncalculator.view.ReactionControlView
import be.nepravsky.sm.evereactioncalculator.view.ReactionInformationView
import be.nepravsky.sm.evereactioncalculator.view.ReactionItemsView
import be.nepravsky.sm.evereactioncalculator.view.ShareReactionDialog
import be.nepravsky.sm.uikit.theme.AppTheme
import be.nepravsky.sm.uikit.theme.colors.gradient1
import be.nepravsky.sm.uikit.theme.colors.gradient2
import be.nepravsky.sm.uikit.view.icons.SmallIcon


@OptIn(ExperimentalFoundationApi::class)
@Composable
fun ReactorScreen(
    viewModel: ReactorViewModel,
    router: ReactorRouter,
) {
    val focusManager = LocalFocusManager.current
    val context = LocalContext.current

    val state = viewModel.state.collectAsState()

    var isShowReactionInformation by remember { mutableStateOf(true) }
    val pagerState = rememberPagerState(pageCount = { ReactionTab.entries.size })
    val selectedTabIndex = pagerState.currentPage

    LaunchedEffect(null) {
        focusManager.clearFocus()
        viewModel.sideEffect.collect{ effect ->
            when{
                effect is ReactorSideEffect.ShareReaction -> {
                    val sendIntent = Intent(Intent.ACTION_SEND).apply {
                        putExtra(Intent.EXTRA_TEXT, effect.text)
                        type = "text/plain"
                    }
                    val shareIntent = Intent.createChooser(sendIntent, null)
                    startActivity(context, shareIntent, null)
                }
                else -> {
                    //do nothing
                }
            }

        }
    }


    if (state.value.isShowShareDialog) ShareReactionDialog(
        onSimpleTextShare = {
            viewModel.shareAsSimpleText(selectedTabIndex == ReactionTab.BASE_TYPE.ordinal)
        },
        onRichTextShare = {
            viewModel.shareAsEveNoteText(selectedTabIndex == ReactionTab.BASE_TYPE.ordinal)
        },
        onDismiss = { viewModel.hideShareDialog() }
    )

    Column(
        modifier = Modifier.background(AppTheme.colors.foreground)
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
                        .clickable {
                            isShowReactionInformation = isShowReactionInformation.not()
                        },
                    imageVector = if (isShowReactionInformation)
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
                visible = isShowReactionInformation,
            ) {
                ReactionInformationView(state, selectedTabIndex)
            }

            AnimatedVisibility(
                visible = state.value.isSingleReaction
            ) {
                ReactionControlView(viewModel)
            }
        }


        ReactionItemsView(selectedTabIndex, pagerState, state, gradient1, gradient2)
    }
}
