package ru.glindaquint.everwell.screens.feed

import android.annotation.SuppressLint
import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxScope
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.selection.TextSelectionColors
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Clear
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.Done
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.material.icons.filled.ShoppingCart
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.IntSize
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import kotlinx.coroutines.delay
import ru.glindaquint.everwell.dto.colors.MainTopBarColors
import ru.glindaquint.everwell.navigation.main.MainRoutes
import ru.glindaquint.everwell.network.dto.feedProducts.FeedProductDto
import ru.glindaquint.everwell.network.dto.product.ProductDto
import ru.glindaquint.everwell.sharedComponents.EverwellScaffold
import ru.glindaquint.everwell.sharedComponents.MainTopBar
import ru.glindaquint.everwell.ui.theme.FeedAlternateSecondary
import ru.glindaquint.everwell.ui.theme.FeedBackground
import ru.glindaquint.everwell.ui.theme.FeedPrimary
import ru.glindaquint.everwell.ui.theme.FeedSecondary
import ru.glindaquint.everwell.utils.FeedType
import ru.glindaquint.everwell.utils.ProductFilterType
import ru.glindaquint.everwell.viewModels.impl.SearchProductViewModel
import kotlin.math.cos
import kotlin.math.sin

@Suppress("ktlint:standard:function-naming")
@Composable
fun ProductSearchScreen(navHostController: NavHostController) {
    val productName = remember { mutableStateOf(TextFieldValue()) }
    val viewModel = hiltViewModel<SearchProductViewModel>()
    val products = viewModel.products.collectAsState()

    val filterType = remember { mutableStateOf(ProductFilterType.ALL) }

    val feedType = remember { mutableStateOf(FeedType.UNSET) }
    val selectedProducts = remember { mutableListOf<FeedProductDto>() }

    LaunchedEffect(productName.value, filterType.value) {
        delay(500)
        viewModel.filterProducts(filterType = filterType.value) {
            it.lowercase().contains(
                productName.value.text.lowercase(),
            )
        }
    }

    LaunchedEffect(Unit) {
        val product =
            navHostController.previousBackStackEntry?.savedStateHandle?.get<FeedProductDto>("selected_product")

        val savedProducts =
            navHostController.previousBackStackEntry?.savedStateHandle?.get<List<FeedProductDto>>(
                "saved_selected_products",
            )

        val savedFeedType =
            navHostController.previousBackStackEntry?.savedStateHandle?.get<FeedType>("feed_type")

        product?.let {
            selectedProducts.add(it)
            navHostController.currentBackStackEntry?.savedStateHandle?.remove<FeedProductDto>("selected_product")
        }

        savedProducts?.let {
            selectedProducts.addAll(it)
            navHostController.currentBackStackEntry?.savedStateHandle?.remove<FeedProductDto>("saved_selected_products")
        }

        savedFeedType?.let {
            feedType.value = savedFeedType
            navHostController.currentBackStackEntry?.savedStateHandle?.remove<FeedType>("feed_type")
        }
    }

    DisposableEffect(Unit) {
        onDispose {
            navHostController.currentBackStackEntry?.savedStateHandle?.set(
                "saved_selected_products",
                selectedProducts,
            )
            navHostController.currentBackStackEntry?.savedStateHandle?.set(
                "feed_type",
                feedType.value,
            )
        }
    }

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
            ProductSearchFloatingActionButton(
                onAddClick = {
                    navHostController.navigate(MainRoutes.feedAddProduct.routeName)
                },
                onCartClick = {
                    navHostController.currentBackStackEntry?.savedStateHandle?.set(
                        "selected_products",
                        selectedProducts,
                    )
                    navHostController.navigate(MainRoutes.feedCart.routeName)
                },
                onSaveClick = {
                    viewModel.saveFeed(
                        feedType = feedType.value,
                        products = selectedProducts,
                        onSuccess = {
                            navHostController.navigate(MainRoutes.feed.routeName)
                        },
                    )
                    selectedProducts.clear()
                },
                cartIndicatorValue = selectedProducts.size,
            )
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
                        modifier = Modifier.padding(end = 5.dp).size(24.dp),
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
            ProductsListFilterButton(value = "All", onClick = {
                filterType.value = ProductFilterType.ALL
            })
            ProductsListFilterButton(value = "Recent", onClick = {
                filterType.value = ProductFilterType.RECENT
            })
            ProductsListFilterButton(value = "My", onClick = {
                filterType.value = ProductFilterType.MY
            })
        }
        Column(verticalArrangement = Arrangement.spacedBy(5.dp)) {
            products.value.forEach { product ->
                ProductsListItem(product = product, onClick = {
                    navHostController.navigate("${MainRoutes.feedProductInfo.routeName}/${product.productId}")
                })
            }
        }
    }
}

@Suppress("ktlint:standard:function-naming")
@Composable
fun ProductSearchFloatingActionButton(
    cartIndicatorValue: Int = 0,
    onAddClick: () -> Unit,
    onCartClick: () -> Unit,
    onSaveClick: () -> Unit,
) {
    val buttonsData =
        listOf(
            Pair(0, 1),
            Pair(45, 2),
            Pair(90, 3),
        )

    val expanded = remember { mutableStateOf(false) }
    val animatedSubButtonScale = remember { Animatable(0f) }
    val mainButtonSize = remember { mutableStateOf(IntSize(0, 0)) }

    LaunchedEffect(expanded.value) {
        if (expanded.value) {
            animatedSubButtonScale.animateTo(
                targetValue = 1f,
                animationSpec = tween(200),
            )
        } else {
            animatedSubButtonScale.animateTo(
                targetValue = 0f,
                animationSpec = tween(200),
            )
        }
    }

    Box {
        FloatingActionButton(
            onClick = { expanded.value = !expanded.value },
            shape = CircleShape,
            containerColor = FeedPrimary,
            contentColor = Color.White,
            modifier =
                Modifier.onGloballyPositioned {
                    mainButtonSize.value = it.size
                },
        ) {
            Icon(
                imageVector = if (expanded.value) Icons.Filled.Close else Icons.Filled.MoreVert,
                contentDescription = "add own product",
            )
        }

        buttonsData.forEach { (angle, id) ->
            SideActionButton(
                parentViewSize = mainButtonSize.value,
                angle = angle.toFloat(),
                icon =
                    when (id) {
                        1 -> Icons.Filled.Add
                        2 -> Icons.Filled.ShoppingCart
                        else -> Icons.Filled.Done
                    },
                onClick = {
                    when (id) {
                        1 -> onAddClick()
                        2 -> onCartClick()
                        else -> onSaveClick()
                    }
                },
                indicatorValue =
                    when (id) {
                        1 -> 0
                        2 -> cartIndicatorValue
                        else -> 0
                    },
                modifier = Modifier.scale(animatedSubButtonScale.value),
            )
        }
    }
}

@Suppress("ktlint:standard:function-naming")
@Composable
fun BoxScope.SideActionButton(
    parentViewSize: IntSize,
    angle: Float,
    icon: ImageVector,
    modifier: Modifier = Modifier,
    indicatorValue: Int = 0,
    onClick: () -> Unit,
) {
    val radians = Math.toRadians(angle.toDouble())
    val x = (parentViewSize.height / 2.3f * cos(radians)).dp
    val y = (parentViewSize.height / 2.3f * sin(radians)).dp

    Box(
        contentAlignment = Alignment.TopEnd,
        modifier = Modifier.align(Alignment.BottomEnd).offset(x = -x, y = -y).then(modifier),
    ) {
        FloatingActionButton(
            containerColor = FeedPrimary,
            contentColor = Color.White,
            onClick = onClick,
            shape = CircleShape,
        ) {
            Icon(
                imageVector = icon,
                contentDescription = "icons for each action",
            )
        }
        if (indicatorValue != 0) {
            Box(
                modifier = Modifier.size(21.dp).background(color = Color.Red, shape = CircleShape),
                contentAlignment = Alignment.Center,
            ) {
                Text(text = indicatorValue.toString(), color = Color.White, fontSize = 10.sp)
            }
        }
    }
}

@SuppressLint("DefaultLocale")
@Suppress("ktlint:standard:function-naming")
@Composable
fun ProductsListItem(
    product: ProductDto,
    onClick: () -> Unit,
) {
    Column(
        modifier =
            Modifier
                .fillMaxWidth()
                .background(
                    color = FeedAlternateSecondary,
                    shape = RoundedCornerShape(12.dp),
                ).clip(RoundedCornerShape(12.dp))
                .clickable { onClick() }
                .padding(top = 3.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        Text(
            text = product.title,
            modifier = Modifier.fillMaxWidth(0.95f),
            fontWeight = FontWeight.Medium,
        )
        Spacer(
            modifier = Modifier.fillMaxWidth().height(2.dp).background(FeedPrimary),
        )
        Text(
            modifier = Modifier.fillMaxWidth(0.95f).padding(vertical = 7.dp),
            text =
                buildAnnotatedString {
                    withStyle(SpanStyle(FeedPrimary)) {
                        append("Б: ")
                    }
                    append(String.format("%-5.1f", product.protein))

                    withStyle(SpanStyle(FeedPrimary)) {
                        append("  Ж: ")
                    }
                    append(String.format("%-5.1f", product.fat))

                    withStyle(SpanStyle(FeedPrimary)) {
                        append("  У: ")
                    }
                    append(String.format("%-5.1f", product.carbohydrates))

                    withStyle(SpanStyle(FeedPrimary)) {
                        append("  К: ")
                    }
                    append(String.format("%-4d", product.calories))
                },
            fontFamily = FontFamily.Monospace,
            fontSize = 13.5.sp,
        )
    }
}

@SuppressLint("DefaultLocale")
@Suppress("ktlint:standard:function-naming")
@Composable
fun FeedProductsListItem(
    product: FeedProductDto,
    onClick: () -> Unit,
) {
    Column(
        modifier =
            Modifier
                .fillMaxWidth()
                .background(
                    color = FeedAlternateSecondary,
                    shape = RoundedCornerShape(12.dp),
                ).clip(RoundedCornerShape(12.dp))
                .clickable { onClick() }
                .padding(top = 3.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        Text(
            text = product.product?.title.toString(),
            modifier = Modifier.fillMaxWidth(0.95f),
            fontWeight = FontWeight.Medium,
        )
        Spacer(
            modifier = Modifier.fillMaxWidth().height(2.dp).background(FeedPrimary),
        )
        Text(
            modifier = Modifier.fillMaxWidth(0.95f).padding(vertical = 7.dp),
            text =
                buildAnnotatedString {
                    withStyle(SpanStyle(FeedPrimary)) {
                        append("Б: ")
                    }
                    append(String.format("%-5.1f", product.protein * product.quantity))

                    withStyle(SpanStyle(FeedPrimary)) {
                        append("  Ж: ")
                    }
                    append(String.format("%-5.1f", product.fat * product.quantity))

                    withStyle(SpanStyle(FeedPrimary)) {
                        append("  У: ")
                    }
                    append(String.format("%-5.1f", product.carbohydrates * product.quantity))

                    withStyle(SpanStyle(FeedPrimary)) {
                        append("  К: ")
                    }
                    append(String.format("%-4d", product.calories * product.quantity))
                },
            fontFamily = FontFamily.Monospace,
            fontSize = 13.5.sp,
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
        modifier = Modifier.fillMaxWidth().padding(0.dp),
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
