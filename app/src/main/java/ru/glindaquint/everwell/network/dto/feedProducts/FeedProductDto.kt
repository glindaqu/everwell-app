package ru.glindaquint.everwell.network.dto.feedProducts

import ru.glindaquint.everwell.network.dto.product.ProductDto

data class FeedProductDto(
    val feedProductsId: Long,
    val product: ProductDto,
    val quantity: Int,
    val portionSize: Int,
    val protein: Float,
    val carbohydrates: Float,
    val fat: Float,
)
