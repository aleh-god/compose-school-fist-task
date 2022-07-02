package by.godevelopment.randomizer.ui.composefragments

import android.util.Log
import androidx.compose.foundation.layout.*
import androidx.compose.material.Card
import androidx.compose.material.OutlinedButton
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import by.godevelopment.randomizer.R
import by.godevelopment.randomizer.appcommons.TAG
import by.godevelopment.randomizer.ui.theme.RandomizerTheme

@Composable
fun ResultFragment(
    resultString: String,
    clickReturnButton: () -> Unit
) {
    val value by rememberSaveable { mutableStateOf(resultString) }

    Card(
        modifier = Modifier
            .fillMaxSize()
            .padding(8.dp),
        elevation = 4.dp
    ) {
        Column (modifier = Modifier.fillMaxWidth(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {

            Text(text = stringResource(R.string.fragment_result_header) + value)

            OutlinedButton(
                modifier = Modifier.padding(top = 24.dp),
                onClick = clickReturnButton
            ) {
                Text(
                    text = stringResource(R.string.button_text_return)
                )
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun ResultFragmentPreview() {
    RandomizerTheme {
        ResultFragment(
            resultString= "5",
            clickReturnButton = {
                Log.i(TAG, "ResultFragmentPreview: clickReturnButton")
            }
        )
    }
}