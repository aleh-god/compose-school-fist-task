package by.godevelopment.randomizer

import android.util.Log
import androidx.annotation.StringRes
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import by.godevelopment.randomizer.appcommons.BLANK
import by.godevelopment.randomizer.appcommons.TAG
import by.godevelopment.randomizer.domain.models.ValidationResult
import by.godevelopment.randomizer.domain.usecases.ValidateInputNumericUseCase
import by.godevelopment.randomizer.domain.usecases.ValidateRatioTwoNumericsUseCase
import by.godevelopment.randomizer.ui.models.FragmentEvent
import by.godevelopment.randomizer.ui.models.FragmentState
import kotlin.random.Random

class MainViewModel : ViewModel() {

    private val validateUseCase = ValidateInputNumericUseCase()
    private val validateRatioTwoNumericsUseCase = ValidateRatioTwoNumericsUseCase(validateUseCase)

    private var _fragmentState: MutableLiveData<UiState> = MutableLiveData(UiState())
    val fragmentState: LiveData<UiState> get() = _fragmentState

    fun onEvent(event: FragmentEvent) {
        when(event) {
            is FragmentEvent.InputMinValueChanged -> {
                val resultMin = validateUseCase.execute(event.inputMin)
                Log.i(TAG, "onEvent $resultMin")
                _fragmentState.value = fragmentState.value?.copy(
                    minValue = event.inputMin,
                    minErrorText = resultMin.errorMessage
                )
            }
            is FragmentEvent.InputMaxValueChanged -> {
                val resultMax = validateUseCase.execute(event.inputMax)
                Log.i(TAG, "onEvent $resultMax")
                _fragmentState.value = fragmentState.value?.copy(
                    maxValue = event.inputMax,
                    maxErrorText = resultMax.errorMessage
                )
            }
            is FragmentEvent.PressRandomizeButton -> {
                val result = validateInputValues()
                Log.i(TAG, "onEvent $result")
                if (result.successful) {
                    _fragmentState.value = _fragmentState.value?.copy(
                        fragmentState = FragmentState.Result,
                        resultString = randomize(),
                        mainErrorText = result.errorMessage
                    )
                } else {
                    Log.i(TAG, "onEvent: PressRandomizeButton ${result.errorMessage}")
                    _fragmentState.value = fragmentState.value?.copy(
                        mainErrorText = result.errorMessage
                    )
                }
            }
            is FragmentEvent.PressReturnButton -> {
                _fragmentState.value = _fragmentState.value?.copy(
                    fragmentState = FragmentState.Input
                )
            }
        }
    }

    private fun validateInputValues(): ValidationResult {
        val minValue = fragmentState.value?.minValue ?: BLANK
        val maxValue = fragmentState.value?.maxValue ?: BLANK
        return validateRatioTwoNumericsUseCase.execute(minValue, maxValue)
    }

    private fun randomize(): String {
        return try {
            val minValue = fragmentState.value?.minValue!!.toInt()
            val maxValue = fragmentState.value?.maxValue!!.toInt()
            val randomInt = Random.nextInt(maxValue - minValue) + minValue
            randomInt.toString()
        } catch (e: Exception) {
            Log.i(TAG, "onEvent: Randomize ${e.message}")
            BLANK
        }
    }

    data class UiState(
        val minValue: String = BLANK,
        @StringRes
        val minErrorText: Int? = null,
        @StringRes
        val minLabelText: Int = R.string.label_input_min_integer,

        val maxValue: String = BLANK,
        @StringRes
        val maxErrorText: Int? = null,
        @StringRes
        val maxLabelText: Int = R.string.label_input_max_integer,

        val fragmentState: FragmentState = FragmentState.Input,
        val resultString: String? = null,
        @StringRes
        val mainErrorText: Int? = null
    )
}