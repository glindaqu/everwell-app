package ru.glindaquint.everwell.network.dto.feedProducts

import android.os.Parcelable
import com.google.gson.Gson
import kotlinx.parcelize.Parcelize
import ru.glindaquint.everwell.network.dto.product.ProductDto

@Parcelize
data class FeedProductDto(
    val feedProductsId: Long? = null,
    val product: ProductDto? = null,
    val quantity: Int,
    val portionSize: Int,
    val protein: Float,
    val carbohydrates: Float,
    val fat: Float,
) : Parcelable {
    fun toJson(): String = Gson().toJson(this)

    companion object {
        fun fromJson(json: String): FeedProductDto = Gson().fromJson(json, FeedProductDto::class.java)
    }
}
