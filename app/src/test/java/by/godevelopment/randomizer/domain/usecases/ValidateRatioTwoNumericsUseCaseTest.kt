package by.godevelopment.randomizer.domain.usecases

import by.godevelopment.randomizer.R
import org.junit.Assert.*

import org.junit.Test

class ValidateRatioTwoNumericsUseCaseTest {

    private var sut: ValidateRatioTwoNumericsUseCase = ValidateRatioTwoNumericsUseCase(
        ValidateInputNumericUseCase()
    )

    @Test
    fun validate_input_blank() {
        val res = sut.execute(minValue = "min", maxValue = "max")
        assertEquals(false, res.successful)
        assertEquals(R.string.message_error_validate_input_blank, res.errorMessage)
    }

    @Test
    fun validate_integer_max() {
        val res = sut.execute(minValue = "1234", maxValue = "12")
        assertEquals(false, res.successful)
        assertEquals(R.string.message_error_validate_integer_max, res.errorMessage)
    }

    @Test
    fun validate_correct_input() {
        val res = sut.execute(minValue = "12", maxValue = "1234")
        assertEquals(true, res.successful)
    }

    @Test
    fun validate_equal_input() {
        val res = sut.execute(minValue = "12", maxValue = "12")
        assertEquals(false, res.successful)
        assertEquals(R.string.message_error_validate_integer_max, res.errorMessage)
    }
}