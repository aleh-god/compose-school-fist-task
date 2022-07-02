package by.godevelopment.randomizer.domain.usecases

import android.util.Log
import by.godevelopment.randomizer.R
import by.godevelopment.randomizer.appcommons.TAG
import by.godevelopment.randomizer.domain.models.ValidationResult

class ValidateRatioTwoNumericsUseCase(
    private val validateUseCase: ValidateInputNumericUseCase
) {
    fun execute(minValue: String, maxValue: String): ValidationResult {

        val resultMin = validateUseCase.execute(minValue)
        val resultMax = validateUseCase.execute(maxValue)
        if (!resultMin.successful || !resultMax.successful) return ValidationResult(
            successful = false,
            errorMessage = R.string.message_error_validate_input_blank
        )

        return  try {
            val minInt = minValue.toInt()
            val maxInt = maxValue.toInt()
            if (minInt < maxInt) ValidationResult(successful = true)
            else {
                ValidationResult(
                    successful = false,
                    errorMessage = R.string.message_error_validate_integer_max
                )
            }
        } catch (e: Exception) {
            Log.i(TAG, "ValidateRatioTwoNumericsUseCase execute: ${e.message}")
            ValidationResult(
                successful = false,
                errorMessage = R.string.message_error_validate_input_blank
            )
        }
    }
}