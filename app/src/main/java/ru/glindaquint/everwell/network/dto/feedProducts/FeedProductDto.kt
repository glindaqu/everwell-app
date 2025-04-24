package ru.glindaquint.everwell.network.dto.feedProducts

import android.os.Parcelable
import kotlinx.parcelize.Parcelize
import ru.glindaquint.everwell.network.dto.product.ProductDto

@Parcelize
data class FeedProductDto(
    val feedProductsId: Long? = null,
    val product: ProductDto? = null,
    val quantity: Int,
    val weightInGrams: Int,
    val calories: Int,
    val protein: Double,
    val carbohydrates: Double,
    val fat: Double,
) : Parcelable
