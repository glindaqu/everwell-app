package ru.glindaquint.everwell.network.dto.product

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class ProductDto(
    val productId: Long,
    val title: String,
    val protein: Float,
    val fat: Float,
    val carbohydrates: Float,
    val calories: Int,
    val weightInGrams: Int,
) : Parcelable
