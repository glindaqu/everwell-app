package ru.glindaquint.everwell.network.dto.product

data class InsertProductRequest(
    val title: String,
    val weightInGrams: Int,
    val fat: Float,
    val protein: Float,
    val carbohydrates: Float,
    val calories: Int,
)
