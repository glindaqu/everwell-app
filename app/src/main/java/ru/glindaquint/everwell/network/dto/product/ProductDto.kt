package ru.glindaquint.everwell.network.dto.product

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class ProductDto(
    val productId: Long,
    val title: String,
    val protein: Double,
    val fat: Double,
    val carbohydrates: Double,
    val calories: Int,
    val weightInGrams: Int,
) : Parcelable
