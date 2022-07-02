package by.godevelopment.randomizer.ui.composefragments

import android.util.Log
import androidx.annotation.StringRes
import androidx.compose.foundation.layout.*
import androidx.compose.material.Card
import androidx.compose.material.MaterialTheme
import androidx.compose.material.OutlinedButton
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import by.godevelopment.randomizer.R
import by.godevelopment.randomizer.appcommons.BLANK
import by.godevelopment.randomizer.appcommons.TAG
import by.godevelopment.randomizer.ui.composablecommons.InputNumericUiHolder
import by.godevelopment.randomizer.ui.theme.RandomizerTheme

@Composable
fun InputFragment(
    modifier: Modifier = Modifier,
    previousValue: String = BLANK,
    minValue: String = BLANK,
    maxValue: String = BLANK,
    @StringRes
    minErrorText: Int? = null,
    @StringRes
    maxErrorText: Int? = null,
    @StringRes
    minLabelText: Int? = null,
    @StringRes
    maxLabelText: Int? = null,
    @StringRes
    mainErrorText: Int? = null,
    onClickRandomizeButtonEvent: () -> Unit,
    onMaxValueChangeEvent: (String) -> Unit,
    onMinValueChangeEvent: (String) -> Unit
) {
    Card(
        modifier = modifier
            .fillMaxSize()
            .padding(8.dp),
        elevation = 4.dp
    ) {
        Column(
            modifier = Modifier.fillMaxWidth(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Top
        ) {

            Text(
                modifier = Modifier.padding(top = 24.dp),
                text = stringResource(R.string.fragment_input_header) + previousValue
            )

            InputNumericUiHolder(
                minValue = minValue,
                maxValue = maxValue,
                minErrorText = minErrorText,
                maxErrorText = maxErrorText,
                minLabelText = minLabelText,
                maxLabelText = maxLabelText,
                onMinValueChange = onMinValueChangeEvent,
                onMaxValueChange = onMaxValueChangeEvent
            )

            mainErrorText?.let {
                Text(
                    modifier = Modifier.paddingFromBaseline(top = 26.dp),
                    fontSize = 14.sp,
                    color = MaterialTheme.colors.error,
                    text = stringResource(it)
                )
            }

            OutlinedButton(
                modifier = Modifier.padding(top = 24.dp),
                onClick = onClickRandomizeButtonEvent
            ) {
                Text(
                    text = stringResource(R.string.button_text_randomize)
                )
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun InputFragmentPreview() {
    RandomizerTheme {
        InputFragment(
            previousValue = "5",
            minValue = "1234",
            minErrorText = R.string.message_error_validate_input_blank,
            maxValue = "4321",
            mainErrorText = R.string.message_error_validate_integer_max,
            onClickRandomizeButtonEvent = {
                Log.i(TAG, "InputFragmentPreview: onClickRandomizeButtonEvent")
            },
            onMinValueChangeEvent = {
                Log.i(TAG, "InputFragmentPreview: onMinValueChangeEvent $it")
            },
            onMaxValueChangeEvent = {
                Log.i(TAG, "InputFragmentPreview: onMaxValueChangeEvent $it")
            },
        )
    }
}