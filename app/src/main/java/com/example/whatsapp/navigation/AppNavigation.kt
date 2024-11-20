package com.example.whatsapp.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.whatsapp.screens.ChatScreen
import com.example.whatsapp.screens.InicioScreen

@Composable
fun AppNavigation(modifier: Modifier) {
    val navControlador = rememberNavController()
    NavHost(navController = navControlador, startDestination = AppScreen.IncioScreen.route) {
        composable(AppScreen.IncioScreen.route) {
            InicioScreen(navControlador, modifier)
        }
        composable(AppScreen.ChatScreen.route
                //+ "/{contact}", arguments = listOf(navArgument(name = "") {
            //type = NavType.StringType
        ) {
            ChatScreen(
                navControlador,
                modifier)
        }
    }
}