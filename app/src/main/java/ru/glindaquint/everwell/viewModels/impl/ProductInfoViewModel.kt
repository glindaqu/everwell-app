package ru.glindaquint.everwell.viewModels.impl

import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import ru.glindaquint.everwell.network.dto.feed.ProductDto
import javax.inject.Inject

@HiltViewModel
class ProductInfoViewModel
    @Inject
    constructor() : ViewModel() {
        val product = MutableStateFlow<ProductDto?>(null)

        fun searchProduct(productId: Long) {
        }
    }
