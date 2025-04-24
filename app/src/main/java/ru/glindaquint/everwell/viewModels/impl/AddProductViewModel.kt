package ru.glindaquint.everwell.viewModels.impl

import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import ru.glindaquint.everwell.network.dto.product.InsertProductRequest
import ru.glindaquint.everwell.services.ProductService
import javax.inject.Inject

@HiltViewModel
class AddProductViewModel
    @Inject
    constructor(
        private val productsService: ProductService,
    ) : ViewModel() {
        fun insertProduct(
            request: InsertProductRequest,
            onSuccess: (() -> Unit)? = null,
            onFailure: ((Throwable) -> Unit)? = null,
        ) {
            productsService.insertProduct(
                request = request,
                onSuccess = onSuccess,
                onFailure = onFailure,
            )
        }
    }
