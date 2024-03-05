package com.example.opnchallenge.ui.store

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel

@Composable
fun StoreScreen(
    modifier: Modifier = Modifier,
    viewModel: StoreViewModel = hiltViewModel()
) {

    val uiState by viewModel.uiState.collectAsState()

    uiState.store?.let { store -> Text(store.name) }
    Scaffold(
        topBar = { StoreTopAppBar(title = uiState.store?.name ?: "Store name") }, bottomBar = {}
    ) { paddingValues ->
        Box(modifier = Modifier.padding(paddingValues))

    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun StoreTopAppBar(
    modifier: Modifier = Modifier,
    title: String
) {
    Column(
    ) {
        CenterAlignedTopAppBar(
            modifier = modifier,
            title = { Text(title) })
        Text(text = "operate time")
    }

}

@Composable
fun StoreBottomAppBar(
    modifier: Modifier = Modifier,
) {
    BottomAppBar(
        modifier = modifier
    ) {

    }
}


