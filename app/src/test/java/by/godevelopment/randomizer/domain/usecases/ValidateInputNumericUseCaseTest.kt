package by.godevelopment.randomizer.domain.usecases

import by.godevelopment.randomizer.R
import org.junit.Assert.*

import org.junit.Test

class ValidateInputNumericUseCaseTest {

    private val sut: ValidateInputNumericUseCase = ValidateInputNumericUseCase()

    @Test
    fun validate_input_blank() {
        val res = sut.execute("max")
        assertEquals(false, res.successful)
        assertEquals(R.string.message_error_validate_input_blank, res.errorMessage)
    }

    @Test
    fun validate_integer_max() {
        val res = sut.execute("1234567890987654321")
        assertEquals(false, res.successful)
        assertEquals(R.string.message_error_validate_input_blank, res.errorMessage)
    }

    @Test
    fun validate_correct_input() {
        val res = sut.execute("1234")
        assertEquals(true, res.successful)
    }

    @Test
    fun validate_integer_zero() {
        val res = sut.execute("0")
        assertEquals(false, res.successful)
        assertEquals(R.string.message_error_validate_integer_zero, res.errorMessage)
    }
}