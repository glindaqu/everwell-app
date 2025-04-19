package ru.glindaquint.everwell.network.dto.product

data class ProductDto(
    val id: Long,
    val title: String,
    val protein: Float,
    val fat: Float,
    val carbohydrates: Float,
    val calories: Int,
    val weightInGrams: Int,
)