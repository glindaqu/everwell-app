package ru.glindaquint.everwell.screens.feed

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

@Suppress("ktlint:standard:function-naming")
@Composable
fun ProductInfoScreen(
    navHostController: NavHostController,
    productId: Long,
) {
    val viewModel = hiltViewModel<ProductInfoViewModel>()
    val product = viewModel.product.collectAsState()

    val fats = remember { mutableStateOf(TextFieldValue()) }
    val calories = remember { mutableStateOf(TextFieldValue()) }
    val carbohydrates = remember { mutableStateOf(TextFieldValue()) }
    val protein = remember { mutableStateOf(TextFieldValue()) }
    val portionsCount = remember { mutableStateOf(TextFieldValue()) }
    val portionSize = remember { mutableStateOf(TextFieldValue()) }

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
        viewModel.searchProduct(productId)
    }

    EverwellScaffold(
        containerColor = FeedBackground,
        topBar = {
            MainTopBar(
                icon = Icons.AutoMirrored.Filled.ArrowBack,
                title = product.value?.title.toString(),
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
                )
                SquareTextField(
                    title = "Fat",
                    value = protein.value,
                    subtitle = "",
                    showMeasurementUnits = false,
                    onValueChanged = {
                        protein.value = it
                    },
                    colors = colors,
                )
                SquareTextField(
                    title = "Cd",
                    value = protein.value,
                    subtitle = "",
                    showMeasurementUnits = false,
                    onValueChanged = {
                        protein.value = it
                    },
                    colors = colors,
                )
                SquareTextField(
                    title = "Cal",
                    value = protein.value,
                    subtitle = "",
                    showMeasurementUnits = false,
                    onValueChanged = {
                        protein.value = it
                    },
                    colors = colors,
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
            action = {},
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
