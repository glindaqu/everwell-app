package ru.glindaquint.everwell.uiStates

data class BottomSheetUiState(
    val isLoading: Boolean = false,
    val isError: Boolean = false,
    val dailyAdvice: String = "",
    val widgetStats: WidgetStats = WidgetStats(),
)

data class WidgetStats(
    val steps: Int = 0,
    val calories: Int = 0,
    val water: Int = 0,
)
