package ru.glindaquint.everwell.network.dto.feed

import ru.glindaquint.everwell.network.dto.feedProducts.FeedProductDto
import ru.glindaquint.everwell.utils.FeedType

data class InsertFeedRequest(
    val feedType: FeedType? = null,
    val products: List<FeedProductDto>? = null,
)
