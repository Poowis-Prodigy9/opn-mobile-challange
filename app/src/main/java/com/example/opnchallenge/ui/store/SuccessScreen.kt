package com.example.opnchallenge.ui.store

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.CheckCircle
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.example.opnchallenge.ui.theme.Success

@Composable
fun SuccessScreen(modifier: Modifier = Modifier, onDismissClick: () -> Unit) {
    Scaffold(modifier = modifier, bottomBar = {
        SuccessBottomAppBar(onDismissClick = onDismissClick)
    }) { paddingValues ->
        Box(
            modifier = Modifier
                .padding(paddingValues)
                .fillMaxSize(),
            contentAlignment = Alignment.Center
        ) {
            Column(
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Icon(
                    Icons.Outlined.CheckCircle,
                    contentDescription = "success",
                    tint = Success,
                    modifier = Modifier.size(60.dp)
                )
                Text(
                    text = "Order success",
                    color = Success,
                    style = TextStyle(fontWeight = FontWeight.Bold)
                )
            }
        }
    }
}

@Composable
fun SuccessBottomAppBar(
    modifier: Modifier = Modifier,
    onDismissClick: () -> Unit,
) {
    BottomAppBar(
        modifier = modifier
    ) {
        Button(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp), onClick = onDismissClick
        ) {
            Text("Dismiss")
        }
    }
}