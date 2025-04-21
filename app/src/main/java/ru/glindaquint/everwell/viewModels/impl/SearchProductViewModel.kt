package ru.glindaquint.everwell.viewModels.impl

import android.util.Log
import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import ru.glindaquint.everwell.network.dto.feed.InsertFeedRequest
import ru.glindaquint.everwell.network.dto.feedProducts.FeedProductDto
import ru.glindaquint.everwell.network.dto.product.ProductDto
import ru.glindaquint.everwell.services.FeedService
import ru.glindaquint.everwell.services.ProductService
import ru.glindaquint.everwell.utils.FeedType
import ru.glindaquint.everwell.utils.ProductFilterType
import javax.inject.Inject

@HiltViewModel
class SearchProductViewModel
    @Inject
    constructor(
        private val feedService: FeedService,
        private val productService: ProductService,
    ) : ViewModel() {
        private val _products = MutableStateFlow(productService.allProducts.value)
        val products = _products.asStateFlow()

        init {
            productService.refreshProducts()
            Log.d("piska", "nu opat blya: ")
        }

        fun filterProducts(
            filterType: ProductFilterType = ProductFilterType.ALL,
            callback: (String) -> Boolean,
        ) {
            when (filterType) {
                ProductFilterType.ALL ->
                    _products.value =
                        productService.allProducts.value.filter { callback(it.title) }

                ProductFilterType.RECENT ->
                    _products.value =
                        productService.recentProducts.value.filter { callback(it.title) }

                ProductFilterType.MY ->
                    _products.value =
                        productService.userProducts.value.filter { callback(it.title) }
            }
        }

        fun findById(productId: Long): ProductDto? =
            productService.allProducts.value.find {
                it.productId == productId
            }

        fun saveFeed(
            feedType: FeedType,
            products: List<FeedProductDto>,
        ) {
            if (products.isEmpty()) {
                return
            }
            feedService.saveFeed(
                request =
                    InsertFeedRequest(
                        feedType = feedType,
                        products = products,
                    ),
            )
        }
    }
