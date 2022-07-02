package by.godevelopment.randomizer.ui.composablecommons

import android.util.Log
import androidx.annotation.StringRes
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.paddingFromBaseline
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.MaterialTheme
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import by.godevelopment.randomizer.R
import by.godevelopment.randomizer.appcommons.TAG
import by.godevelopment.randomizer.ui.theme.RandomizerTheme

@Composable
fun InputNumericUiItem(
    modifier: Modifier = Modifier,
    initValue: String,
    @StringRes
    initLabelText: Int? = null,
    @StringRes
    initErrorText: Int? = null,
    onValueChange: (String) -> Unit
) {
    Column(modifier = modifier) {
        OutlinedTextField(
            modifier = Modifier
                .padding(horizontal = 16.dp)
                .padding(top = 8.dp),
            value = initValue,
            onValueChange = { onValueChange.invoke(it) },
            isError = initErrorText != null,
            label = { initLabelText?.let { Text(text = stringResource(it)) } },
            keyboardOptions = KeyboardOptions.Default.copy(keyboardType = KeyboardType.Number),
            singleLine = true
        )
        initErrorText?.let {
            Text(
                modifier = Modifier
                    .padding(start = 16.dp)
                    .paddingFromBaseline(top = 16.dp),
                fontSize = 12.sp,
                color = MaterialTheme.colors.error,
                text = stringResource(it)
            )
        }
    }
}

@Composable
fun InputNumericUiHolder(
    modifier: Modifier = Modifier,
    minValue: String,
    maxValue: String,
    @StringRes
    minErrorText: Int? = null,
    @StringRes
    maxErrorText: Int? = null,
    @StringRes
    minLabelText: Int? = R.string.label_input_min_integer,
    @StringRes
    maxLabelText: Int? = R.string.label_input_max_integer,
    onMinValueChange: (String) -> Unit,
    onMaxValueChange: (String) -> Unit
) {

    Column(
        modifier = modifier.fillMaxWidth(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        InputNumericUiItem(
            initValue = minValue,
            onValueChange = onMinValueChange,
            initLabelText = minLabelText,
            initErrorText = minErrorText
        )

        InputNumericUiItem(
            initValue = maxValue,
            onValueChange = onMaxValueChange,
            initLabelText = maxLabelText,
            initErrorText = maxErrorText
        )
    }

}

@Preview(showBackground = true)
@Composable
fun InputNumericUiHolderPreview() {
    RandomizerTheme {
        InputNumericUiHolder(
            Modifier.padding(8.dp),
            minValue = "1234567",
            maxValue = "4321",
            maxErrorText = R.string.message_error_validate_integer_max,
            minLabelText = R.string.label_input_min_integer,
            maxLabelText = R.string.label_input_max_integer,
            onMinValueChange = { Log.i(TAG, "EditTextPreview 1: $it") },
            onMaxValueChange = { Log.i(TAG, "EditTextPreview 2: $it") }
        )
    }
}
