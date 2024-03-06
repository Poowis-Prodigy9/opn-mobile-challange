package com.example.opnchallenge.ui.store

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.widthIn
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Star
import androidx.compose.material.icons.rounded.Remove
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.Button
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FilledIconButton
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import coil.compose.AsyncImage
import com.example.opnchallenge.model.totalCount

@Composable
fun StoreScreen(
    modifier: Modifier = Modifier,
    viewModel: StoreViewModel = hiltViewModel(),
    onOrderClick: () -> Unit,
) {
    val uiState by viewModel.uiState.collectAsState()

    Scaffold(
        modifier = modifier,
        topBar = {
            StoreTopAppBar(title = uiState.store?.name ?: "",
                rating = uiState.store?.rating,
                detail = uiState.store?.let { store ->
                    "Open: ${timeText(store.openingTime)} - ${
                        timeText(
                            store.closingTime
                        )
                    }"
                })
        },
        bottomBar = {
            StoreBottomAppBar(
                onOrderClick = onOrderClick,
                enabled = (uiState.products?.totalCount() ?: 0) > 0
            )
        })
    { paddingValues ->
        Box(modifier = Modifier.padding(paddingValues)) {
            if (uiState.products == null) {
                Box(
                    contentAlignment = Alignment.Center, modifier = Modifier.fillMaxSize()
                ) {
                    CircularProgressIndicator()
                }
            } else {
                LazyColumn(
                    contentPadding = PaddingValues(16.dp),
                    verticalArrangement = Arrangement.spacedBy(8.dp)
                ) {
                    itemsIndexed(uiState.products!!) { index, product ->
                        ProductCard(
                            name = product.product.name,
                            price = product.product.price,
                            imageUrl = product.product.imageUrl,
                            count = product.count,
                            onUpdateCount = { viewModel.updateProductCount(index, it) },
                            onMinusClick = {
                                viewModel.updateProductCount(
                                    index,
                                    product.count - 1
                                )
                            },
                            onPlusClick = { viewModel.updateProductCount(index, product.count + 1) }
                        )
                    }
                }
            }

        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ProductCard(
    modifier: Modifier = Modifier,
    name: String,
    price: Double,
    imageUrl: String,
    count: Int,
    onUpdateCount: (Int) -> Unit,
    onMinusClick: () -> Unit,
    onPlusClick: () -> Unit,
) {
    ElevatedCard(
        modifier = modifier
            .fillMaxWidth()
    ) {

        Row(
            modifier = Modifier
                .fillMaxSize()
                .height(IntrinsicSize.Max)
                .padding(8.dp),
            horizontalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            AsyncImage(
                modifier = Modifier
                    .size(120.dp)
                    .fillMaxWidth(0.33f),
                contentScale = ContentScale.Crop,
                model = imageUrl,
                contentDescription = name
            )
            Column(
                modifier = Modifier.fillMaxSize()
            ) {
                Text(name)
                Text("${price}฿")
                Spacer(modifier = Modifier.weight(1f))
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.spacedBy(4.dp, Alignment.End),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    FilledIconButton(
                        enabled = count > 0,
                        onClick = onMinusClick,
                    ) {
                        Icon(Icons.Rounded.Remove, "decrease product")
                    }
                    OutlinedTextField(
                        modifier = Modifier
                            .widthIn(30.dp, 60.dp)
                            .padding(0.dp),
                        colors = TextFieldDefaults.colors(
                            focusedContainerColor = Color.White,
                            unfocusedContainerColor = Color.White
                        ),
                        textStyle = TextStyle(
                            fontWeight = FontWeight.Bold,
                            fontSize = 20.sp,
                            textAlign = TextAlign.Center
                        ),
                        keyboardOptions = KeyboardOptions(
                            keyboardType = KeyboardType.Number,
                            imeAction = ImeAction.Done
                        ),
                        singleLine = true,
                        value = "$count",
                        onValueChange = { onUpdateCount(it.toIntOrNull() ?: 0) },

                        )
                    FilledIconButton(
                        onClick = onPlusClick
                    ) {
                        Icon(Icons.Default.Add, "increase product")
                    }
                }
            }

        }
    }
}

@Preview
@Composable
fun PreviewProductCard() {
    ProductCard(
        name = "Latte",
        price = 50.0,
        imageUrl = "https://www.nespresso.com/ncp/res/uploads/recipes/nespresso-recipes-Latte-Art-Tulip.jpg",
        count = 0,
        onUpdateCount = {},
        onMinusClick = {},
        onPlusClick = {},
    )
}


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun StoreTopAppBar(
    modifier: Modifier = Modifier,
    title: String,
    rating: Double? = null,
    detail: String? = null,
) {
    Column(
        modifier = modifier.background(MaterialTheme.colorScheme.primaryContainer)
    ) {
        CenterAlignedTopAppBar(
            title = {
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.spacedBy(4.dp)
                ) {
                    Text(title)
                    rating?.let {
                        Spacer(modifier = Modifier.width(8.dp))
                        Text("$rating")
                        Icon(
                            imageVector = Icons.Default.Star,
                            contentDescription = "star rating",
                            tint = Color(0xFFFFD700),
                        )
                    }
                }
            },
            colors = TopAppBarDefaults.topAppBarColors(
                containerColor = MaterialTheme.colorScheme.primaryContainer
            )
        )
        detail?.let {
            Text(
                text = detail,
                modifier = Modifier.padding(start = 16.dp, end = 16.dp, bottom = 16.dp)
            )
        }
    }
}

@Composable
fun StoreBottomAppBar(
    modifier: Modifier = Modifier,
    onOrderClick: () -> Unit,
    enabled: Boolean,
) {
    BottomAppBar(
        modifier = modifier,
        containerColor = MaterialTheme.colorScheme.primaryContainer
    ) {
        Button(
            enabled = enabled,
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp), onClick = onOrderClick
        ) {
            Text("Order")
        }
    }
}


fun timeText(timeString: String): String {
    return timeString.split(".").first()
}
