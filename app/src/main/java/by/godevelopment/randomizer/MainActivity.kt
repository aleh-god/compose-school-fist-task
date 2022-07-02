package by.godevelopment.randomizer

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import by.godevelopment.randomizer.appcommons.TAG
import by.godevelopment.randomizer.ui.composefragments.InputFragment
import by.godevelopment.randomizer.ui.composefragments.ResultFragment
import by.godevelopment.randomizer.ui.models.FragmentEvent
import by.godevelopment.randomizer.ui.models.FragmentState
import by.godevelopment.randomizer.ui.theme.RandomizerTheme

class MainActivity : ComponentActivity() {

    private val viewModel: MainViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel.fragmentState.observe(this) { uiState ->
            Log.i(TAG, "onCreate: $uiState")
            setContent {
                RandomizerTheme {
                    when(uiState.fragmentState) {

                        FragmentState.Input -> {
                            InputFragment(
                                previousValue = uiState.resultString ?: getString(R.string.result_didnt_randomize),
                                minValue = uiState.minValue,
                                maxValue = uiState.maxValue,
                                minErrorText = uiState.minErrorText,
                                maxErrorText = uiState.maxErrorText,
                                mainErrorText = uiState.mainErrorText,
                                minLabelText = uiState.minLabelText,
                                maxLabelText = uiState.maxLabelText,
                                onClickRandomizeButtonEvent = {
                                    viewModel.onEvent(FragmentEvent.PressRandomizeButton)
                                    Log.i(TAG, "FragmentEvent.PressRandomizeButton ")
                                },
                                onMaxValueChangeEvent = {
                                    viewModel.onEvent(FragmentEvent.InputMaxValueChanged(it))
                                    Log.i(TAG, "FragmentEvent.InputMaxValueChanged $it")
                                },
                                onMinValueChangeEvent = {
                                    Log.i(TAG, "FragmentEvent.InputMinValueChanged $it")
                                    viewModel.onEvent(FragmentEvent.InputMinValueChanged(it))
                                }
                            )
                        }

                        FragmentState.Result -> {
                            ResultFragment(
                                resultString = uiState.resultString ?: getString(R.string.result_didnt_randomize),
                                clickReturnButton = {
                                    Log.i(TAG, "FragmentEvent.clickReturnButton")
                                    viewModel.onEvent(FragmentEvent.PressReturnButton)
                                }
                            )
                        }
                    }
                }
            }
        }
    }
}
