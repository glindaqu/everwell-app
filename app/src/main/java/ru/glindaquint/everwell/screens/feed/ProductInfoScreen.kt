package ru.glindaquint.everwell.screens.feed

import android.util.Log
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.selection.TextSelectionColors
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import ru.glindaquint.everwell.dto.colors.MainTopBarColors
import ru.glindaquint.everwell.dto.colors.SquareTextFieldColors
import ru.glindaquint.everwell.navigation.main.MainRoutes
import ru.glindaquint.everwell.network.dto.feedProducts.FeedProductDto
import ru.glindaquint.everwell.sharedComponents.EverwellActionButton
import ru.glindaquint.everwell.sharedComponents.EverwellScaffold
import ru.glindaquint.everwell.sharedComponents.MainTopBar
import ru.glindaquint.everwell.sharedComponents.squareTextField.SquareTextField
import ru.glindaquint.everwell.ui.theme.FeedAccent
import ru.glindaquint.everwell.ui.theme.FeedAlternateSecondary
import ru.glindaquint.everwell.ui.theme.FeedBackground
import ru.glindaquint.everwell.ui.theme.FeedPrimary
import ru.glindaquint.everwell.ui.theme.FeedSecondary
import ru.glindaquint.everwell.viewModels.impl.ProductInfoViewModel
import ru.glindaquint.everwell.viewModels.impl.SearchProductViewModel

@Suppress("ktlint:standard:function-naming")
@Composable
fun ProductInfoScreen(
    navHostController: NavHostController,
    productId: Long,
) {
    val productViewModel = hiltViewModel<ProductInfoViewModel>()
    val searchViewModel = hiltViewModel<SearchProductViewModel>()
    val uiState = productViewModel.uiState.collectAsState()

    val fats = remember { mutableStateOf(TextFieldValue(uiState.value.fats)) }
    val calories = remember { mutableStateOf(TextFieldValue(uiState.value.calories)) }
    val carbohydrates = remember { mutableStateOf(TextFieldValue(uiState.value.carbohydrates)) }
    val protein = remember { mutableStateOf(TextFieldValue(uiState.value.protein)) }
    val portionsCount = remember { mutableStateOf(TextFieldValue("1")) }
    val portionSize = remember { mutableStateOf(TextFieldValue(uiState.value.portionSize)) }

    val colors =
        SquareTextFieldColors(
            backgroundColor = FeedAccent,
            contentColor = FeedSecondary,
            cursorColor = FeedPrimary,
            focusedLabelColor = FeedSecondary,
            unfocusedLabelColor = FeedSecondary,
            pointerColor = FeedPrimary,
        )

    LaunchedEffect(Unit) {
        productViewModel.loadProduct(productId)
    }

    LaunchedEffect(uiState.value) {
        fats.value = TextFieldValue(uiState.value.fats)
        calories.value = TextFieldValue(uiState.value.calories)
        carbohydrates.value = TextFieldValue(uiState.value.carbohydrates)
        protein.value = TextFieldValue(uiState.value.protein)
        portionSize.value = TextFieldValue(uiState.value.portionSize)
    }

    EverwellScaffold(
        containerColor = FeedBackground,
        topBar = {
            MainTopBar(
                icon = Icons.AutoMirrored.Filled.ArrowBack,
                title = uiState.value.title,
                colors =
                    MainTopBarColors(
                        backgroundColor = FeedPrimary,
                        foregroundColor = Color.White,
                        behindContainerColor = FeedBackground,
                    ),
                onIconClick = {
                    navHostController.navigateUp()
                },
            )
        },
        contentPadding = PaddingValues(10.dp),
        contentSpacing = Arrangement.SpaceBetween,
    ) {
        Column(verticalArrangement = Arrangement.spacedBy(10.dp)) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(16.dp),
            ) {
                SquareTextField(
                    title = "Pt",
                    value = protein.value,
                    subtitle = "",
                    showMeasurementUnits = false,
                    onValueChanged = {
                        protein.value = it
                    },
                    colors = colors,
                    fontSizeFactor = 20f,
                )
                SquareTextField(
                    title = "Fat",
                    value = fats.value,
                    subtitle = "",
                    showMeasurementUnits = false,
                    onValueChanged = {
                        fats.value = it
                    },
                    colors = colors,
                    fontSizeFactor = 20f,
                )
                SquareTextField(
                    title = "Cd",
                    value = carbohydrates.value,
                    subtitle = "",
                    showMeasurementUnits = false,
                    onValueChanged = {
                        carbohydrates.value = it
                    },
                    colors = colors,
                    fontSizeFactor = 20f,
                )
                SquareTextField(
                    title = "Cal",
                    value = calories.value,
                    subtitle = "",
                    showMeasurementUnits = false,
                    onValueChanged = {
                        calories.value = it
                    },
                    colors = colors,
                    fontSizeFactor = 20f,
                )
            }
            ProductParamTextField(
                value = portionsCount.value,
                onValueChanged = {
                    portionsCount.value = it
                },
                leadingIcon = {
                    Text(
                        text = "Portions count",
                        color = FeedSecondary,
                        modifier = Modifier.padding(start = 12.dp),
                    )
                },
            )
            ProductParamTextField(
                value = portionSize.value,
                onValueChanged = {
                    portionSize.value = it
                },
                leadingIcon = {
                    Text(
                        text = "Portion size",
                        color = FeedSecondary,
                        modifier = Modifier.padding(start = 12.dp),
                    )
                },
                trailingIcon = {
                    Text(text = "gr")
                },
            )
        }
        EverwellActionButton(
            text = "Next",
            backgroundColor = FeedPrimary,
            foregroundColor = FeedAlternateSecondary,
            paddingValues = PaddingValues(0.dp),
            action = {
                val productToSend =
                    FeedProductDto(
                        product = searchViewModel.findById(productId),
                        carbohydrates = carbohydrates.value.text.toFloat(),
                        fat = fats.value.text.toFloat(),
                        protein = protein.value.text.toFloat(),
                        portionSize = portionSize.value.text.toInt(),
                        quantity = portionsCount.value.text.toInt(),
                    )

                Log.d("chlen", "Отправляемый продукт: $productToSend") // Логируем

                navHostController.currentBackStackEntry?.savedStateHandle?.set(
                    "selected_product",
                    productToSend,
                )

                navHostController.navigate(MainRoutes.feedSearchProduct.routeName)
            },
        )
    }
}

@Suppress("ktlint:standard:function-naming")
@Composable
fun ProductParamTextField(
    leadingIcon: (@Composable () -> Unit)? = null,
    trailingIcon: (@Composable () -> Unit)? = null,
    value: TextFieldValue,
    onValueChanged: (TextFieldValue) -> Unit,
) {
    TextField(
        modifier = Modifier.fillMaxWidth(),
        value = value,
        onValueChange = onValueChanged,
        trailingIcon = trailingIcon,
        leadingIcon = leadingIcon,
        shape = RoundedCornerShape(12.dp),
        singleLine = true,
        textStyle =
            TextStyle(
                textAlign = TextAlign.End,
                color = FeedPrimary,
                fontSize = 16.sp,
            ),
        colors =
            TextFieldDefaults.colors(
                focusedIndicatorColor = Color.Transparent,
                unfocusedIndicatorColor = Color.Transparent,
                cursorColor = FeedPrimary,
                selectionColors =
                    TextSelectionColors(
                        handleColor = FeedPrimary,
                        backgroundColor = FeedPrimary.copy(0.5f),
                    ),
                unfocusedContainerColor = FeedAlternateSecondary,
                focusedContainerColor = FeedAlternateSecondary,
            ),
    )
}
