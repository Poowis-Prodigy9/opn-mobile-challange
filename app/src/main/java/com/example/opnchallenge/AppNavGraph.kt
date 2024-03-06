package com.example.opnchallenge

import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.opnchallenge.ui.store.CartScreen
import com.example.opnchallenge.ui.store.StoreScreen
import com.example.opnchallenge.ui.store.SuccessScreen


@Composable
fun AppNavGraph(
    modifier: Modifier = Modifier,
    navController: NavHostController = rememberNavController(),
    startDestination: String = AppDestinations.STORE_ROUTE,
) {
    NavHost(
        navController = navController,
        startDestination = startDestination,
        modifier = modifier
    ) {
        composable(AppDestinations.STORE_ROUTE) {
            StoreScreen(
                onOrderClick = { navController.navigate(AppDestinations.CART_ROUTE) }
            )

        }

        composable(AppDestinations.CART_ROUTE) { entry ->
            val parentEntry = remember(entry) {
                navController.getBackStackEntry(AppDestinations.STORE_ROUTE)
            }
            CartScreen(
                viewModel = hiltViewModel(parentEntry),
                onBack = { navController.popBackStack() },
                onOrderComplete = { navController.navigate(AppDestinations.SUCCESS_ROUTE) }
            )
        }


        composable(AppDestinations.SUCCESS_ROUTE) {
            SuccessScreen(
                onDismissClick = {
                    navController.popBackStack(AppDestinations.STORE_ROUTE, false)
                }
            )
        }
    }
}