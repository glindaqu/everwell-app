package ru.glindaquint.everwell.network.dto.feed

import ru.glindaquint.everwell.utils.FeedType

data class InsertFeedRequest(
    val feedType: FeedType? = null,
    val productsIds: Set<Long>? = null,
    val quantity: Int? = null,
    val portionSize: Int? = null,
    val protein: Float? = null,
    val carbohydrates: Float? = null,
    val fat: Float? = null,
)
