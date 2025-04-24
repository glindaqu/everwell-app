package ru.glindaquint.everwell.services

import dagger.hilt.android.scopes.ActivityRetainedScoped
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import ru.glindaquint.everwell.network.dto.DataResponse
import ru.glindaquint.everwell.network.dto.product.InsertProductRequest
import ru.glindaquint.everwell.network.dto.product.ProductDto
import ru.glindaquint.everwell.network.services.ProductsNetworkService
import javax.inject.Inject

@ActivityRetainedScoped
class ProductService
    @Inject
    constructor(
        private val productsNetworkService: ProductsNetworkService,
    ) {
        private val _allProducts = MutableStateFlow<List<ProductDto>>(emptyList())
        private val _userProducts = MutableStateFlow<List<ProductDto>>(emptyList())
        private val _recentProducts = MutableStateFlow<List<ProductDto>>(emptyList())

        val allProducts: StateFlow<List<ProductDto>> = _allProducts.asStateFlow()
        val userProducts: StateFlow<List<ProductDto>> = _userProducts.asStateFlow()
        val recentProducts: StateFlow<List<ProductDto>> = _recentProducts.asStateFlow()

        fun refreshProducts(
            onSuccess: (() -> Unit)? = null,
            onFailure: ((Throwable) -> Unit)? = null,
        ) {
            productsNetworkService
                .getAllProducts()
                .enqueue(
                    object : Callback<DataResponse<List<ProductDto>>> {
                        override fun onResponse(
                            call: Call<DataResponse<List<ProductDto>>?>,
                            response: Response<DataResponse<List<ProductDto>>?>,
                        ) {
                            if (response.isSuccessful) {
                                _allProducts.value = response.body()?.data ?: emptyList()
                                onSuccess?.invoke()
                            } else {
                                onFailure?.invoke(Throwable(response.errorBody()?.string()))
                            }
                        }

                        override fun onFailure(
                            call: Call<DataResponse<List<ProductDto>>?>,
                            t: Throwable,
                        ) {
                            onFailure?.invoke(t)
                        }
                    },
                )

            productsNetworkService
                .getOwnedProductsByUser()
                .enqueue(
                    object : Callback<DataResponse<List<ProductDto>>> {
                        override fun onResponse(
                            call: Call<DataResponse<List<ProductDto>>?>,
                            response: Response<DataResponse<List<ProductDto>>?>,
                        ) {
                            if (response.isSuccessful) {
                                _userProducts.value = response.body()?.data ?: emptyList()
                                onSuccess?.invoke()
                            } else {
                                onFailure?.invoke(Throwable(response.errorBody()?.string()))
                            }
                        }

                        override fun onFailure(
                            call: Call<DataResponse<List<ProductDto>>?>,
                            t: Throwable,
                        ) {
                            onFailure?.invoke(t)
                        }
                    },
                )

            productsNetworkService
                .getRecentProducts()
                .enqueue(
                    object : Callback<DataResponse<List<ProductDto>>> {
                        override fun onResponse(
                            call: Call<DataResponse<List<ProductDto>>?>,
                            response: Response<DataResponse<List<ProductDto>>?>,
                        ) {
                            if (response.isSuccessful) {
                                _recentProducts.value = response.body()?.data ?: emptyList()
                                onSuccess?.invoke()
                            } else {
                                onFailure?.invoke(Throwable(response.errorBody()?.string()))
                            }
                        }

                        override fun onFailure(
                            call: Call<DataResponse<List<ProductDto>>?>,
                            t: Throwable,
                        ) {
                            onFailure?.invoke(t)
                        }
                    },
                )
        }

        fun findById(id: Long): ProductDto? {
            if (_allProducts.value.isEmpty()) return null

            return _allProducts.value.find { it.productId == id }
        }

        fun insertProduct(
            request: InsertProductRequest,
            onSuccess: (() -> Unit)? = null,
            onFailure: ((Throwable) -> Unit)? = null,
        ) {
            productsNetworkService.addProduct(request = request).enqueue(
                object : Callback<Void> {
                    override fun onResponse(
                        call: Call<Void>,
                        response: Response<Void>,
                    ) {
                        if (response.isSuccessful) {
                            refreshProducts()
                            onSuccess?.invoke()
                        } else {
                            onFailure?.invoke(Throwable("Unknown error"))
                        }
                    }

                    override fun onFailure(
                        call: Call<Void>,
                        t: Throwable,
                    ) {
                        onFailure?.invoke(t)
                    }
                },
            )
        }
    }
