package com.example.whatsapp.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.whatsapp.Contacto
import com.example.whatsapp.screens.ChatScreen
import com.example.whatsapp.screens.InicioScreen
import com.google.gson.Gson

@Composable
fun AppNavigation(modifier: Modifier) {
    val navControlador = rememberNavController()

    NavHost(navController = navControlador, startDestination = AppScreen.IncioScreen.route) {
        composable(AppScreen.IncioScreen.route) {
            InicioScreen(navControlador, modifier)
        }
        composable(AppScreen.ChatScreen.route
                + "/{contact}", arguments = listOf(navArgument(name = "contact") {
            type = NavType.StringType
        })) {
            val gson = Gson()
            val contactoJson = it.arguments?.getString("contact")
            val contacto = gson.fromJson(contactoJson, Contacto::class.java)
            ChatScreen(
                navControlador,
                modifier,
                contacto)
        }
    }
}