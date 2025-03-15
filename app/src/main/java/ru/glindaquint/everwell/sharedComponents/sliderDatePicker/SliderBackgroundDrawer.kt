package ru.glindaquint.everwell.sharedComponents.sliderDatePicker

import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.graphics.drawscope.DrawScope

fun DrawScope.drawSliderBackground(
    brush: Brush,
    topLeftRadius: Float,
    topRightRadius: Float,
    bottomRightRadius: Float,
    bottomLeftRadius: Float,
) {
    val path =
        Path().apply {
            moveTo(topLeftRadius, 0f) // Начинаем с верхнего левого угла
            lineTo(size.width - topRightRadius, 0f) // Верхний край
            arcTo(
                rect =
                    androidx.compose.ui.geometry.Rect(
                        size.width - topRightRadius * 2,
                        0f,
                        size.width,
                        topRightRadius * 2,
                    ),
                startAngleDegrees = 270f,
                sweepAngleDegrees = 90f,
                forceMoveTo = false,
            )
            lineTo(size.width, size.height - bottomRightRadius) // Правый край
            arcTo(
                rect =
                    androidx.compose.ui.geometry.Rect(
                        size.width - bottomRightRadius * 6,
                        size.height - bottomRightRadius * 1.1f,
                        size.width,
                        size.height,
                    ),
                startAngleDegrees = 0f,
                sweepAngleDegrees = 90f,
                forceMoveTo = false,
            )
            lineTo(bottomLeftRadius, size.height) // Нижний край
            arcTo(
                rect =
                    androidx.compose.ui.geometry.Rect(
                        0f,
                        size.height - bottomLeftRadius * 1.1f,
                        bottomLeftRadius * 6,
                        size.height,
                    ),
                startAngleDegrees = 90f,
                sweepAngleDegrees = 90f,
                forceMoveTo = false,
            )
            lineTo(0f, topLeftRadius) // Левый край
            arcTo(
                rect =
                    androidx.compose.ui.geometry
                        .Rect(0f, 0f, topLeftRadius * 2, topLeftRadius * 2),
                startAngleDegrees = 180f,
                sweepAngleDegrees = 90f,
                forceMoveTo = false,
            )
            close() // Замыкаем путь
        }

    drawPath(path = path, brush = brush) // Задаем цвет фона
}
