package by.godevelopment.randomizer.domain.models

import androidx.annotation.StringRes

data class ValidationResult(
    val successful: Boolean,
    @StringRes
    val errorMessage: Int? = null
)
