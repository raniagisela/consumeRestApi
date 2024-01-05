package com.example.consumerestapi.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.consumerestapi.ui.kontak.screen.DestinasiEntry
import com.example.consumerestapi.ui.kontak.screen.DestinasiHome
import com.example.consumerestapi.ui.kontak.screen.DetailDestination
import com.example.consumerestapi.ui.kontak.screen.DetailScreen
import com.example.consumerestapi.ui.kontak.screen.EditDestination
import com.example.consumerestapi.ui.kontak.screen.EntryKontakScreen
import com.example.consumerestapi.ui.kontak.screen.HomeScreen
import com.example.consumerestapi.ui.kontak.screen.ItemEditScreen


@Composable
fun PengelolaHalaman(
    navController: NavHostController = rememberNavController()
) {
    NavHost(
        navController = navController,
        startDestination = DestinasiHome.route,
        modifier = Modifier,
    ) {
        composable(DestinasiHome.route) {
            HomeScreen(navigateToItemEntry = {
                navController.navigate(DestinasiEntry.route)
            },
                onDetailClick = {})
        }
        composable(DestinasiEntry.route) {
            EntryKontakScreen(navigateBack = {
                navController.navigate(
                    DestinasiHome.route
                ) {
                    popUpTo(DestinasiHome.route) {
                        inclusive = true
                    }
                }
            })
        }
        composable(DetailDestination.routeWithArgs,
            arguments = listOf(navArgument(DetailDestination.kontakId) {
                type = NavType.IntType
            })
        ) { backStackEntry ->
            val itemId = backStackEntry.arguments?.getInt(DetailDestination.kontakId)
            itemId?.let {
                DetailScreen(
                    navigateBack = {
                        navController.navigateUp()
                    },
                    onEditClick = { itemId ->
                        navController.navigate("${EditDestination.route}/$itemId")
                        println(itemId)
                    },

                    )
            }
        }
        composable(
            EditDestination.routeWithArgs,
            arguments = listOf(navArgument(EditDestination.kontakId) {
                type = NavType.IntType
            })
        ) {
            ItemEditScreen(navigateBack = { navController.popBackStack() },
                onNavigateUp = {
                    navController.navigate(DestinasiHome.route) {
                        popUpTo(DestinasiHome.route) {
                            inclusive = true
                        }
                    }
                })
        }
        composable(DestinasiHome.route) {
            HomeScreen(navigateToItemEntry = {
                navController.navigate(DestinasiEntry.route)
            },
                onDetailClick = { itemId ->
                    navController.navigate("${DetailDestination.route}/$itemId")
                    println(itemId)
                })
        }
    }
}