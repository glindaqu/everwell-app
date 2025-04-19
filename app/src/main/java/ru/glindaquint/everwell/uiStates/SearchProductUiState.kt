package ru.glindaquint.everwell.uiStates

import ru.glindaquint.everwell.network.dto.product.ProductDto

data class SearchProductUiState(
    val allProducts: List<ProductDto> = emptyList(),
    val recentProducts: List<ProductDto> = emptyList(),
    val userProducts: List<ProductDto> = emptyList(),
    val error: String? = null,
    val success: Boolean = false
)
