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
import be.nepravsky.sm.domain.utils.TEXT_EMPTY
import be.nepravsky.sm.evereactioncalculator.ReactorViewModel
import be.nepravsky.sm.evereactioncalculator.uikit.R
import be.nepravsky.sm.uikit.view.textfield.CBaseTextField
import be.nepravsky.sm.uikit.view.textfield.decimalKeyboardOptions

@Composable
fun ReactionControlView(viewModel: ReactorViewModel) {

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
            label = stringResource(R.string.feature_run_count),
            value = runText,
            onValueChange = { runs ->
                val run: Long? = runs.toLongOrNull()
                runText = run?.toString() ?: TEXT_EMPTY
                viewModel.setRuns(run ?: 0)
            },
            keyboardOptions = decimalKeyboardOptions,
        )

        CBaseTextField(
            modifier = Modifier,
            label = stringResource(R.string.feature_reactor_me_percent),
            value = meText,
            onValueChange = { mes ->
                val me = mes.toDoubleOrNull()
                meText = if (me == 0.0) TEXT_EMPTY else me?.toString() ?: TEXT_EMPTY
                viewModel.setMe(me ?: 0.0)
            },
            keyboardOptions = decimalKeyboardOptions,
        )

        CBaseTextField(
            modifier = Modifier,
            label = stringResource(R.string.feature_reactor_sub_me_percent),
            value = subMeText,
            onValueChange = { subMes ->
                val me = subMes.toDoubleOrNull()
                subMeText = if (me == 0.0) TEXT_EMPTY else me?.toString() ?: TEXT_EMPTY
                viewModel.setSubMe(me ?: 0.0)
            },
            keyboardOptions = decimalKeyboardOptions,
        )
    }
}