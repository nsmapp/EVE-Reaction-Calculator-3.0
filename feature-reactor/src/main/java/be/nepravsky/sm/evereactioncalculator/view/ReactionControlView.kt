package be.nepravsky.sm.evereactioncalculator.view

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import be.nepravsky.sm.evereactioncalculator.uikit.R
import be.nepravsky.sm.uikit.view.textfield.CBaseTextField
import be.nepravsky.sm.uikit.view.textfield.numberKeyboardOptions

@Composable
fun ReactionControlView(
    run: String,
    me: String,
    subMe: String,
    isMeEnabled: Boolean,
    onRunChanged: (run: Long) -> Unit,
    onMeChanged: (me: Double) -> Unit,
    onSubMeChanged: (subMe: Double) -> Unit,
) {

    var runText by remember { mutableStateOf("1") }
    var meText by remember { mutableStateOf("0") }
    var subMeText by remember { mutableStateOf("0") }

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .wrapContentHeight(),
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        CBaseTextField(
            modifier = Modifier.wrapContentSize(),
            label = stringResource(R.string.feature_run_count, run),
            value = runText,
            onValueChange = { runs ->
                if (runs.length <= 4){
                    runText = runs
                    val run: Long? = runs.toLongOrNull()
                    onRunChanged(run ?: 0)
                }
            },
            keyboardOptions = numberKeyboardOptions,
            textAlign = TextAlign.Center,
        )

        CBaseTextField(
            modifier = Modifier,
            label = stringResource(R.string.feature_reactor_me_percent, me),
            value = meText,
            onValueChange = { mes ->
                if (mes.length <= 4){
                    meText = mes
                    if (mes.isEmpty()) onMeChanged(0.0)
                    else mes.toDoubleOrNull()
                        ?.let { onMeChanged(it) }
                }
            },
            keyboardOptions = numberKeyboardOptions,
            textAlign = TextAlign.Center,
            enabled = isMeEnabled,
        )

        CBaseTextField(
            modifier = Modifier,
            label = stringResource(R.string.feature_reactor_sub_me_percent, subMe),
            value = subMeText,
            onValueChange = { subMes ->
                if (subMes.length <= 4){
                    subMeText = subMes
                    if (subMeText.isEmpty()) onSubMeChanged(0.0)
                    else subMes.toDoubleOrNull()
                        ?.let { onSubMeChanged(it) }
                }
            },
            keyboardOptions = numberKeyboardOptions,
            textAlign = TextAlign.Center,
            enabled = isMeEnabled,
        )
    }
}