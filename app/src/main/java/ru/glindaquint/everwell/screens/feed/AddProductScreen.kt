package ru.glindaquint.everwell.screens.feed

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import ru.glindaquint.everwell.dto.colors.MainTopBarColors
import ru.glindaquint.everwell.dto.colors.SquareTextFieldColors
import ru.glindaquint.everwell.navigation.main.MainRoutes
import ru.glindaquint.everwell.network.dto.product.InsertProductRequest
import ru.glindaquint.everwell.sharedComponents.EverwellActionButton
import ru.glindaquint.everwell.sharedComponents.EverwellScaffold
import ru.glindaquint.everwell.sharedComponents.MainTopBar
import ru.glindaquint.everwell.sharedComponents.squareTextField.SquareTextField
import ru.glindaquint.everwell.ui.theme.FeedAccent
import ru.glindaquint.everwell.ui.theme.FeedAlternateSecondary
import ru.glindaquint.everwell.ui.theme.FeedBackground
import ru.glindaquint.everwell.ui.theme.FeedPrimary
import ru.glindaquint.everwell.ui.theme.FeedSecondary
import ru.glindaquint.everwell.viewModels.impl.AddProductViewModel

@Suppress("ktlint:standard:function-naming")
@Composable
fun ProductAddScreen(navHostController: NavHostController) {
    val fats = remember { mutableStateOf(TextFieldValue()) }
    val calories = remember { mutableStateOf(TextFieldValue()) }
    val carbohydrates = remember { mutableStateOf(TextFieldValue()) }
    val protein = remember { mutableStateOf(TextFieldValue()) }
    val weightInGrams = remember { mutableStateOf(TextFieldValue()) }
    val title = remember { mutableStateOf(TextFieldValue()) }

    val viewModel = hiltViewModel<AddProductViewModel>()

    val colors =
        SquareTextFieldColors(
            backgroundColor = FeedAccent,
            contentColor = FeedSecondary,
            cursorColor = FeedPrimary,
            focusedLabelColor = FeedSecondary,
            unfocusedLabelColor = FeedSecondary,
            pointerColor = FeedPrimary,
        )

    EverwellScaffold(
        contentPadding = PaddingValues(10.dp),
        containerColor = FeedBackground,
        contentSpacing = Arrangement.SpaceBetween,
        topBar = {
            MainTopBar(
                icon = Icons.AutoMirrored.Filled.ArrowBack,
                title = "Add product",
                colors =
                    MainTopBarColors(
                        backgroundColor = FeedPrimary,
                        foregroundColor = Color.White,
                        behindContainerColor = FeedBackground,
                    ),
                onIconClick = { navHostController.navigateUp() },
            )
        },
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
                value = title.value,
                onValueChanged = {
                    title.value = it
                },
                leadingIcon = {
                    Text(
                        text = "Title",
                        color = FeedSecondary,
                        modifier = Modifier.padding(start = 12.dp),
                    )
                },
            )
            ProductParamTextField(
                value = weightInGrams.value,
                onValueChanged = {
                    weightInGrams.value = it
                },
                leadingIcon = {
                    Text(
                        text = "Weight",
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
                viewModel.insertProduct(
                    request =
                        InsertProductRequest(
                            title = title.value.text,
                            fat = fats.value.text.toFloat(),
                            protein = protein.value.text.toFloat(),
                            carbohydrates = carbohydrates.value.text.toFloat(),
                            weightInGrams = weightInGrams.value.text.toInt(),
                            calories = calories.value.text.toInt(),
                        ),
                    onSuccess = {
                        navHostController.navigate(MainRoutes.feedSearchProduct.routeName)
                    },
                )
            },
        )
    }
}
