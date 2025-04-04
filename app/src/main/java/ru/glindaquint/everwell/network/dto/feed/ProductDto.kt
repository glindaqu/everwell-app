package ru.glindaquint.everwell.network.dto.feed

data class ProductDto(
    val id: Long,
    val title: String,
    val protein: Float,
    val fats: Float,
    val carbohydrates: Float,
    val calories: Int,
    val weight: Int,
)
