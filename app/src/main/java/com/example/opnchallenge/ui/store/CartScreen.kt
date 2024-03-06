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
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.Button
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import coil.compose.AsyncImage
import com.example.opnchallenge.model.totalPrice

@Composable
fun CartScreen(
    modifier: Modifier = Modifier,
    viewModel: StoreViewModel = hiltViewModel(),
    onBack: () -> Unit,
    onOrderComplete: () -> Unit,
) {
    val uiState by viewModel.uiState.collectAsState()

    Scaffold(modifier = modifier, topBar = {
        CartTopAppBar(onBack = onBack)
    }, bottomBar = {
        CartBottomAppBar(
            onConfirmClick = { viewModel.createOrder(onOrderComplete) },
            totalPrice = uiState.products?.totalPrice() ?: 0.0,
            enabled = !uiState.isOrderLoading
        )
    }) { paddingValues ->
        Box(
            modifier = Modifier
                .padding(paddingValues)
        ) {
            Column(
                modifier = Modifier.padding(16.dp)
            ) {
                Text("Address")
                OutlinedTextField(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(top = 16.dp),
                    label = { Text("Address") },
                    minLines = 3,
                    maxLines = 3,
                    keyboardOptions = KeyboardOptions(imeAction = ImeAction.Done),
                    value = uiState.address,
                    onValueChange = viewModel::updateAddress,
                )
                Text("Order summary", modifier = Modifier.padding(top = 16.dp))
                uiState.products?.let {
                    LazyColumn(
                        contentPadding = PaddingValues(vertical = 16.dp),
                        verticalArrangement = Arrangement.spacedBy(8.dp)
                    ) {
                        items(uiState.products!!) { product ->
                            if (product.count > 0) {
                                CartItemCard(
                                    name = product.product.name,
                                    price = product.product.price,
                                    imageUrl = product.product.imageUrl,
                                    totalPrice = product.totalPrice(),
                                    count = product.count
                                )

                            }
                        }
                    }
                }
            }

        }
    }
    if (uiState.isOrderLoading) {
        Box(
            contentAlignment = Alignment.Center,
            modifier = Modifier
                .fillMaxSize()
                .background(Color.White.copy(alpha = 0.8f))
        ) {
            CircularProgressIndicator()
        }
    }
}

@Composable
fun CartItemCard(
    modifier: Modifier = Modifier,
    name: String,
    price: Double,
    count: Int,
    imageUrl: String,
    totalPrice: Double,
) {
    ElevatedCard(
        modifier = modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(8.dp))
            .background(Color.White)
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
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Text("${price}฿")
                    Text("x $count")
                }

                Spacer(modifier = Modifier.weight(1f))
                Row(
                    modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.End
                ) {
                    Text("Total price: ${totalPrice}฿")
                }
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CartTopAppBar(
    modifier: Modifier = Modifier,
    onBack: () -> Unit,
) {
    CenterAlignedTopAppBar(modifier = modifier, title = {
        Text("Order summary")
    }, navigationIcon = {
        IconButton(onClick = onBack) {
            Icon(
                Icons.AutoMirrored.Filled.ArrowBack, contentDescription = "back"
            )
        }
    }, colors = TopAppBarDefaults.topAppBarColors(
        containerColor = MaterialTheme.colorScheme.primaryContainer
    )
    )
}


@Composable
fun CartBottomAppBar(
    modifier: Modifier = Modifier,
    onConfirmClick: () -> Unit,
    totalPrice: Double,
    enabled: Boolean,
) {
    Column(
        modifier = modifier
    ) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .background(MaterialTheme.colorScheme.primaryContainer)
        ) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(start = 16.dp, end = 16.dp, top = 16.dp),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Text("Total price:")
                Text("$totalPrice฿")
            }
        }

        BottomAppBar(
            containerColor = MaterialTheme.colorScheme.primaryContainer
        ) {
            Button(
                enabled = enabled,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp), onClick = onConfirmClick
            ) {
                Text("Confirm")
            }
        }
    }
}


