package by.godevelopment.randomizer.domain.usecases

import by.godevelopment.randomizer.R
import by.godevelopment.randomizer.domain.models.ValidationResult

class ValidateInputNumericUseCase {

    fun execute(input: String?): ValidationResult {
        val key = input?.toIntOrNull()
        return when {
            key == null -> ValidationResult(
                successful = false,
                errorMessage = R.string.message_error_validate_input_blank
            )
            key <= 0 -> ValidationResult(
                successful = false,
                errorMessage = R.string.message_error_validate_integer_zero
            )
            else -> ValidationResult(successful = true)
        }
    }
}
