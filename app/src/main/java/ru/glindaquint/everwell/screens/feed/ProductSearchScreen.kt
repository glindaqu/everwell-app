package ru.glindaquint.everwell.screens.feed

import android.annotation.SuppressLint
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.selection.TextSelectionColors
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Clear
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import ru.glindaquint.everwell.dto.colors.MainTopBarColors
import ru.glindaquint.everwell.network.dto.feed.ProductDto
import ru.glindaquint.everwell.sharedComponents.EverwellScaffold
import ru.glindaquint.everwell.sharedComponents.MainTopBar
import ru.glindaquint.everwell.ui.theme.FeedAlternateSecondary
import ru.glindaquint.everwell.ui.theme.FeedBackground
import ru.glindaquint.everwell.ui.theme.FeedPrimary
import ru.glindaquint.everwell.ui.theme.FeedSecondary
import ru.glindaquint.everwell.viewModels.impl.SearchProductViewModel

@Suppress("ktlint:standard:function-naming")
@Composable
fun ProductSearchScreen(navHostController: NavHostController) {
    val productName = remember { mutableStateOf(TextFieldValue()) }
    val viewModel = hiltViewModel<SearchProductViewModel>()
    val products = viewModel.products.collectAsState()

    EverwellScaffold(
        containerColor = FeedBackground,
        topBar = {
            MainTopBar(
                icon = Icons.AutoMirrored.Filled.ArrowBack,
                title = "Search product",
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
        floatingActionButton = {
            FloatingActionButton(
                onClick = { /*TODO*/ },
                shape = CircleShape,
                containerColor = FeedPrimary,
                contentColor = Color.White,
            ) {
                Icon(imageVector = Icons.Filled.Add, contentDescription = "add own product")
            }
        },
        contentPadding = PaddingValues(10.dp),
        contentSpacing = Arrangement.spacedBy(10.dp),
    ) {
        ProductSearchTextField(
            value = productName.value,
            onValueChanged = {
                productName.value = it
            },
            trailingIcon = {
                if (productName.value.text.isNotEmpty()) {
                    IconButton(
                        onClick = { productName.value = TextFieldValue() },
                        modifier =
                            Modifier
                                .padding(end = 5.dp)
                                .size(24.dp),
                    ) {
                        Icon(
                            imageVector = Icons.Filled.Clear,
                            contentDescription = "clear input",
                            tint = FeedSecondary,
                        )
                    }
                }
            },
        )
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(16.dp),
        ) {
            ProductsListFilterButton(value = "All", onClick = {})
            ProductsListFilterButton(value = "Recent", onClick = {})
            ProductsListFilterButton(value = "My", onClick = {})
        }
        Column(verticalArrangement = Arrangement.spacedBy(5.dp)) {
            products.value.forEach { product ->
                ProductsListItem(product = product)
            }
        }
    }
}

@SuppressLint("DefaultLocale")
@Suppress("ktlint:standard:function-naming")
@Composable
fun ProductsListItem(product: ProductDto) {
    Column(
        modifier =
            Modifier
                .fillMaxWidth()
                .background(
                    color = FeedAlternateSecondary,
                    shape = RoundedCornerShape(12.dp),
                ).padding(top = 3.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        Text(
            text = product.title,
            modifier = Modifier.fillMaxWidth(0.95f),
            fontWeight = FontWeight.Medium,
        )
        Spacer(
            modifier =
                Modifier
                    .fillMaxWidth()
                    .height(2.dp)
                    .background(FeedPrimary),
        )
        Text(
            modifier = Modifier.fillMaxWidth(0.95f).padding(vertical = 7.dp),
            text =
                buildAnnotatedString {
                    withStyle(SpanStyle(FeedBackground)) {
                        append("Б:")
                    }
                    append(String.format(" %-10.2f", product.protein))

                    withStyle(SpanStyle(FeedBackground)) {
                        append("Ж:")
                    }
                    append(String.format(" %-10.2f", product.fats))

                    withStyle(SpanStyle(FeedBackground)) {
                        append("У:")
                    }
                    append(String.format(" %-10.2f", product.carbohydrates))

                    withStyle(SpanStyle(FeedBackground)) {
                        append("К:")
                    }
                    append(String.format("%4d", product.calories))
                },
        )
    }
}

@Suppress("ktlint:standard:function-naming")
@Composable
fun RowScope.ProductsListFilterButton(
    value: String,
    onClick: () -> Unit,
) {
    Button(
        onClick = { onClick() },
        modifier = Modifier.weight(0.1f),
        colors =
            ButtonDefaults.buttonColors(
                containerColor = FeedPrimary,
                contentColor = FeedAlternateSecondary,
            ),
    ) {
        Text(text = value)
    }
}

@Suppress("ktlint:standard:function-naming")
@Composable
fun ProductSearchTextField(
    value: TextFieldValue,
    onValueChanged: (TextFieldValue) -> Unit,
    trailingIcon: (@Composable () -> Unit)? = null,
) {
    TextField(
        modifier =
            Modifier
                .fillMaxWidth()
                .padding(0.dp),
        value = value,
        onValueChange = { onValueChanged(it) },
        shape = CircleShape,
        singleLine = true,
        colors =
            TextFieldDefaults.colors(
                unfocusedContainerColor = FeedAlternateSecondary,
                focusedContainerColor = FeedAlternateSecondary,
                cursorColor = FeedSecondary,
                focusedTextColor = FeedSecondary,
                unfocusedTextColor = FeedSecondary,
                focusedIndicatorColor = Color.Transparent,
                unfocusedIndicatorColor = Color.Transparent,
                selectionColors =
                    TextSelectionColors(
                        handleColor = FeedPrimary,
                        backgroundColor = FeedPrimary.copy(0.5f),
                    ),
            ),
        trailingIcon = { trailingIcon?.invoke() },
    )
}
