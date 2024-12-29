package com.example.serverdatabase.ui.navigation

import android.os.Build
import androidx.annotation.RequiresExtension
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.serverdatabase.ui.view.DestinasiDetail
import com.example.serverdatabase.ui.view.DestinasiEntry
import com.example.serverdatabase.ui.view.DestinasiHome
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
    }
}
