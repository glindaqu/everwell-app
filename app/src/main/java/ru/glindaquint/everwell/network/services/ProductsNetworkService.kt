package ru.glindaquint.everwell.network.services

import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import ru.glindaquint.everwell.network.dto.DataResponse
import ru.glindaquint.everwell.network.dto.product.InsertProductRequest
import ru.glindaquint.everwell.network.dto.product.ProductDto

interface ProductsNetworkService {
    @GET("products/get-all")
    fun getAllProducts(): Call<DataResponse<List<ProductDto>>>

    @GET("products/get-owned-by-user")
    fun getOwnedProductsByUser(): Call<DataResponse<List<ProductDto>>>

    @GET("products/get-recent")
    fun getRecentProducts(): Call<DataResponse<List<ProductDto>>>

    @POST("products/add")
    fun addProduct(
        @Body request: InsertProductRequest,
    ): Call<Void>
}
