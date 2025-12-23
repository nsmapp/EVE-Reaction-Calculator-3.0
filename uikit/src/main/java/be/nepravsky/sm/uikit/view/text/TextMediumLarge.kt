package be.nepravsky.sm.uikit.view.text

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextOverflow
import be.nepravsky.sm.uikit.theme.AppTheme

@Composable
fun TextMediumLarge(
    modifier: Modifier = Modifier,
    text: String,
    color: Color = AppTheme.colors.text,
    overflow: TextOverflow = TextOverflow.Ellipsis,
    softWrap: Boolean = true,
    maxLines: Int = Int.MAX_VALUE,
    minLines: Int = 1,
    style: TextStyle = AppTheme.typography.medium_large
) {
    TextBase(
        modifier = modifier,
        text = text,
        color = color,
        overflow = overflow,
        softWrap = softWrap,
        maxLines = maxLines,
        minLines = minLines,
        style = style
    )
}

@Composable
fun TextMediumLarge(
    modifier: Modifier = Modifier,
    text: Int,
    color: Color = AppTheme.colors.text,
    overflow: TextOverflow = TextOverflow.Ellipsis,
    softWrap: Boolean = true,
    maxLines: Int = Int.MAX_VALUE,
    minLines: Int = 1,
    style: TextStyle = AppTheme.typography.medium_large
) {
    TextBase(
        modifier = modifier,
        text = stringResource(id = text),
        color = color,
        overflow = overflow,
        softWrap = softWrap,
        maxLines = maxLines,
        minLines = minLines,
        style = style
    )
}

@Composable
fun TextMediumLarge(
    modifier: Modifier = Modifier,
    text: AnnotatedString,
    color: Color = AppTheme.colors.text,
    overflow: TextOverflow = TextOverflow.Ellipsis,
    softWrap: Boolean = true,
    maxLines: Int = Int.MAX_VALUE,
    minLines: Int = 1,
    style: TextStyle = AppTheme.typography.medium_large
) {
    TextBase(
        modifier = modifier,
        text = text,
        color = color,
        overflow = overflow,
        softWrap = softWrap,
        maxLines = maxLines,
        minLines = minLines,
        style = style
    )
}