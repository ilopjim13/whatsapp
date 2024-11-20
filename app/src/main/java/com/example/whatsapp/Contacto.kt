package com.example.whatsapp

data class Contacto(val nombre:String, val ultimoMensaje:String, val hora:String, val ultimaConexion:String, val leido:Boolean = true, val isVisibleConexion:Boolean = false, val mensajesEnvidaos:List<String> = listOf(), val mensajesRecibidos:MutableList<String>  = mutableListOf())