package ru.glindaquint.everwell.uiStates

data class FeedUiState(
    val feeds: Feeds = Feeds(),
    val totalCalories: Int = 0,
    val totalFat: Double = 0.0,
    val totalProtein: Double = 0.0,
    val totalCarbohydrates: Double = 0.0,
    val error: String? = null,
    val successful: Boolean = false,
)

data class Feeds(
    val breakfast: List<String> = emptyList(),
    val lunch: List<String> = emptyList(),
    val dinner: List<String> = emptyList(),
    val snack: List<String> = emptyList(),
)
