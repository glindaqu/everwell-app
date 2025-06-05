package ru.glindaquint.everwell.sharedComponents

import android.net.Uri
import androidx.compose.foundation.Image
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import coil3.compose.AsyncImage
import ru.glindaquint.everwell.network.RetrofitFactory

@Suppress("ktlint:standard:function-naming")
@Composable
fun UserImage(
    image: Any?,
    modifier: Modifier = Modifier,
) {
    if (image != null) {
        AsyncImage(
            model =
                when {
                    image is Uri -> image
                    else -> "${RetrofitFactory.API_BASE_URL}$image"
                },
            contentDescription = "User profile image",
            contentScale = ContentScale.Crop,
            modifier = Modifier.then(modifier),
        )
    } else {
        Image(
            imageVector = Icons.Filled.AccountCircle,
            contentDescription = "User profile image",
            modifier = Modifier.then(modifier),
        )
    }
}
