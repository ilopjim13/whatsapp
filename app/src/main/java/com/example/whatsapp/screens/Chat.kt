package com.example.whatsapp.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBackIosNew
import androidx.compose.material.icons.filled.Call
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.material.icons.filled.Videocam
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.whatsapp.Contacto
import com.example.whatsapp.R
import kotlin.math.max

@Composable
fun ChatScreen(navController: NavController,
               modifier: Modifier = Modifier,
               contacto: Contacto = Contacto("PEPE", "","","Ayer",  false, true, mensajesEnvidaos = mutableListOf("Hola que pasa bro que de tiempo", "BUAH YA VES"), mutableListOf("GILIPOLLIA", "JAJAJAJJA")),
               ) {
    ChatBody(navController, modifier, contacto)

}

@Composable
fun ChatBody(navController: NavController,
            modifier: Modifier = Modifier,
            contacto: Contacto) {

    Column(modifier = modifier.fillMaxSize()) {
        CabeceraChat(navController, contacto)

        val numMensajes = max(contacto.mensajesEnvidaos.size, contacto.mensajesRecibidos.size)
        LazyColumn {
            items(numMensajes) {
                if (it <= contacto.mensajesEnvidaos.size) {
                    MensajesEnviados(contacto.mensajesEnvidaos[it])
                }

                if (it <= contacto.mensajesRecibidos.size) {
                    MensajesRecibidos(contacto.mensajesRecibidos[it])
                }
            }
        }




    }

}

@Composable
fun MensajesEnviados(mensaje:String) {
    Column(Modifier.background(Color.Green).width(150.dp), horizontalAlignment = Alignment.Start) {
        Text(mensaje)
    }
}

@Composable
fun MensajesRecibidos(mensaje:String) {
    Column(Modifier.fillMaxWidth(), horizontalAlignment = Alignment.End) {
        Text(mensaje, modifier = Modifier.width(150.dp).background(Color.Green))
    }
}


@Composable
fun CabeceraChat(navController: NavController, contacto: Contacto) {
    Row(Modifier.fillMaxWidth().background(colorResource(id = R.color.fondo)).padding(top = 5.dp, bottom = 5.dp), verticalAlignment = Alignment.CenterVertically, horizontalArrangement = Arrangement.SpaceBetween) {
        Row(verticalAlignment = Alignment.CenterVertically) {
            Icon(imageVector = Icons.Filled.ArrowBackIosNew,
                contentDescription = "Back",
                Modifier.clickable  {navController.popBackStack()},
                tint = colorResource(R.color.white)
            )
            Column {
                Text(contacto.nombre, color = colorResource(R.color.white))
                if(contacto.isVisibleConexion) {
                    Text(contacto.ultimaConexion, color = colorResource(R.color.white))
                }

            }
        }
        Row(verticalAlignment = Alignment.CenterVertically,) {
            Icon(imageVector = Icons.Filled.Videocam, contentDescription = "VideoLLamada", tint = colorResource(R.color.white))
            Icon(imageVector = Icons.Filled.Call, contentDescription = "LLamada", tint = colorResource(R.color.white))
            Icon(imageVector = Icons.Filled.MoreVert, contentDescription = "Opciones", tint = colorResource(R.color.white))
        }
    }
}