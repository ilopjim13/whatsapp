package com.example.whatsapp.navigation

sealed class AppScreen(val route:String) {
    object IncioScreen:AppScreen("Inicio")
    object ChatScreen:AppScreen("Chat")
}