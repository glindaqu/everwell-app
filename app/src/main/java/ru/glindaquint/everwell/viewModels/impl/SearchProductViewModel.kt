package ru.glindaquint.everwell.viewModels.impl

import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import ru.glindaquint.everwell.network.dto.feed.ProductDto
import javax.inject.Inject

@HiltViewModel
class SearchProductViewModel
    @Inject
    constructor() : ViewModel() {
        val products =
            MutableStateFlow(
                listOf(
                    ProductDto(
                        id = 1,
                        title = "Ded",
                        protein = 9f,
                        fats = 6f,
                        carbohydrates = 3f,
                        calories = 23,
                        weight = 30,
                    ),
                    ProductDto(
                        id = 2,
                        title = "Guzova",
                        protein = 9f,
                        fats = 6f,
                        carbohydrates = 3f,
                        calories = 23,
                        weight = 15,
                    ),
                ),
            )
    }
