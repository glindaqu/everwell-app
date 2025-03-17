package ru.glindaquint.everwell.screens.feed.progresses

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.graphics.drawscope.Stroke
import ru.glindaquint.everwell.ui.theme.FeedAccent
import ru.glindaquint.everwell.ui.theme.FeedOnBackground

@Suppress("ktlint:standard:function-naming")
@Composable
fun LinearProgress(value: Float) {
    val trustedValue =
        when {
            value > 1f -> 1f
            else -> value
        }

    Canvas(
        modifier =
            Modifier
                .fillMaxWidth()
                .wrapContentHeight(),
    ) {
        val backgroundScale =
            Path().apply {
                moveTo(0f, 0f)
                lineTo(size.width, 0f)
            }

        val foregroundScale =
            Path().apply {
                moveTo(0f, 0f)
                lineTo(size.width * trustedValue, 0f)
            }

        drawPath(
            path = backgroundScale,
            color = FeedOnBackground,
            style =
                Stroke(
                    width = 10f,
                    cap = StrokeCap.Round,
                ),
        )
        drawPath(
            path = foregroundScale,
            color = FeedAccent,
            style =
                Stroke(
                    width = 10f,
                    cap = StrokeCap.Round,
                ),
        )
    }
}
