package com.example.whatsapp

import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.snapshots.SnapshotStateList

data class Contacto(val nombre:String, var hora:String, val ultimaConexion:String, var leido:Boolean = true, var responder:Boolean = false, val isVisibleConexion:Boolean = false, val mensajesEnviados:SnapshotStateList<String>  = mutableStateListOf(), val mensajesRecibidos:SnapshotStateList<String>  = mutableStateListOf()){
}
