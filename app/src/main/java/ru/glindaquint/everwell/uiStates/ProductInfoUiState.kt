package ru.glindaquint.everwell.uiStates

data class ProductInfoUiState(
    val title: String = "",
    val fats: String = "",
    val calories: String = "",
    val carbohydrates: String = "",
    val protein: String = "",
    val portionsCount: String = "",
    val portionSize: String = "",
    val error: String? = null,
)
