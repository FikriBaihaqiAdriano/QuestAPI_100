package com.example.serverdatabase.ui.navigation

import android.os.Build
import androidx.annotation.RequiresExtension
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.serverdatabase.ui.view.DestinasiDetail
import com.example.serverdatabase.ui.view.DestinasiEdit
import com.example.serverdatabase.ui.view.DestinasiEntry
import com.example.serverdatabase.ui.view.DestinasiHome
import com.example.serverdatabase.ui.view.DetailScreen
import com.example.serverdatabase.ui.view.EditScreen
import com.example.serverdatabase.ui.view.EntryMhsScreen
import com.example.serverdatabase.ui.view.HomeScreen

@RequiresExtension(extension = Build.VERSION_CODES.S, version = 7)
@Composable
fun PengelolaHalaman(navController: NavHostController = rememberNavController()) {
    NavHost(
    navController = navController,
    startDestination = DestinasiHome.route,
    modifier = Modifier,
) {
    composable(DestinasiHome.route) {
        HomeScreen(
            navigateToItemEntry = { navController.navigate(DestinasiEntry.route) },
            onDetailClick = {
                navController.navigate("${DestinasiDetail.route}/$it")
            }
        )
    }
    composable(DestinasiEntry.route) {
        EntryMhsScreen(navigateBack = {
            navController.navigate(DestinasiHome.route) {
                popUpTo(DestinasiHome.route) {
                    inclusive = true

                    }
                }
            })
        }
    composable(DestinasiDetail.routeWithArgs,
            arguments = listOf(
                navArgument(DestinasiDetail.NIM){
                    type = NavType.StringType
                }
            )
        ){
            val nim = it.arguments?.getString(DestinasiDetail.NIM)
            nim?.let { nim ->
                DetailScreen(
                    onBackClick = {
                        navController.popBackStack()
                    },
                    onEditClick = {
                        navController.navigate("${DestinasiEdit.route}/$it")
                    },
                    nim = nim
                )
            }
        }
        composable(
            DestinasiEdit.routesWithArg,
            arguments = listOf(
                navArgument(DestinasiEdit.NIM){
                    type = NavType.StringType
                }
            )
        ){
            val nim = it.arguments?.getString(DestinasiEdit.NIM)
            nim?.let { nim ->
                EditScreen(
                    onBack = {
                        navController.popBackStack()
                    },
                    onNavigate = {
                        navController.navigate(DestinasiHome.route) {
                            popUpTo(DestinasiHome.route) {
                                inclusive = true
                            }
                        }
                    }
                )
            }
        }
    }
}
