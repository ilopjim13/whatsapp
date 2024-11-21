package com.example.whatsapp.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.Send
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.ArrowBackIosNew
import androidx.compose.material.icons.filled.AttachFile
import androidx.compose.material.icons.filled.Call
import androidx.compose.material.icons.filled.CameraAlt
import androidx.compose.material.icons.filled.EmojiEmotions
import androidx.compose.material.icons.filled.Mic
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.material.icons.filled.Videocam
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonColors
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldColors
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.key.Key
import androidx.compose.ui.input.key.key
import androidx.compose.ui.input.key.onKeyEvent
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.whatsapp.Contacto
import com.example.whatsapp.ContactoViewModel
import com.example.whatsapp.R
import kotlin.math.max

@Composable
fun ChatScreen(
    navController: NavController,
    modifier: Modifier = Modifier,
    contacto: Contacto
) {
    ChatBody(navController, modifier, contacto)

}

@Composable
fun ChatBody(
    navController: NavController,
    modifier: Modifier = Modifier,
    contacto: Contacto
) {
    Box(modifier.fillMaxSize()) {
        Image(
            painter = painterResource(R.drawable.fondo),
            contentDescription = "Fondo",
            Modifier.fillMaxSize(),
            contentScale = ContentScale.Crop
        )
        Column(verticalArrangement = Arrangement.SpaceBetween) {
            CabeceraChat(navController, contacto)

            val numMensajes = max(contacto.mensajesEnviados.size, contacto.mensajesRecibidos.size)
            LazyColumn(
                Modifier
                    .height(755.dp)
                    .padding(top = 5.dp)) {
                items(numMensajes) {
                    if (it < contacto.mensajesEnviados.size && contacto.mensajesEnviados[it].isNotBlank()) {
                        MensajesEnviados(contacto.mensajesEnviados[it])
                        Spacer(Modifier.height(16.dp))
                    }

                    if (it < contacto.mensajesRecibidos.size && contacto.mensajesRecibidos[it].isNotBlank()) {
                        MensajesRecibidos(contacto.mensajesRecibidos[it])
                        Spacer(Modifier.height(16.dp))
                    }

                }
            }

            EscribirMensaje(contacto)

        }
    }


}


@Composable
fun MensajesEnviados(mensaje: String) {
    Column(Modifier.padding(start = 16.dp, end = 80.dp), horizontalAlignment = Alignment.Start) {
        Box(
            modifier = Modifier
                .clip(RoundedCornerShape(10.dp))
                .background(colorResource(R.color.mensajeEnviado))
        ) {
            Text(
                mensaje,
                modifier = Modifier.padding(10.dp, top = 5.dp, bottom = 5.dp, end = 15.dp),
                color = Color.White
            )
        }

    }
}

@Composable
fun MensajesRecibidos(mensaje: String) {
    Column(
        Modifier
            .fillMaxWidth()
            .padding(end = 16.dp, start = 80.dp),
        horizontalAlignment = Alignment.End
    ) {
        Box(
            modifier = Modifier
                .clip(RoundedCornerShape(10.dp))
                .background(colorResource(R.color.mensajeRecibido))
        ) {
            Text(
                mensaje,
                modifier = Modifier.padding(10.dp, top = 5.dp, bottom = 5.dp, end = 15.dp),
                color = Color.White
            )
        }
    }
}


@Composable
fun CabeceraChat(navController: NavController, contacto: Contacto) {
    Row(
        Modifier
            .fillMaxWidth()
            .background(colorResource(id = R.color.fondo))
            .padding(top = 5.dp, bottom = 5.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Row(verticalAlignment = Alignment.CenterVertically) {
            Icon(
                imageVector = Icons.Filled.ArrowBackIosNew,
                contentDescription = "Back",
                Modifier
                    .padding(start = 2.dp, end = 2.dp)
                    .clickable { navController.popBackStack() },
                tint = colorResource(R.color.white)
            )
            Icon(
                imageVector = Icons.Filled.AccountCircle,
                contentDescription = "",
                tint = colorResource(R.color.white),
                modifier = Modifier.size(36.dp)
            )
            Column(Modifier.padding(start = 6.dp)) {
                Text(contacto.nombre, color = colorResource(R.color.white))
                if (contacto.isVisibleConexion) {
                    Text(
                        contacto.ultimaConexion,
                        color = colorResource(R.color.white),
                        fontSize = 11.sp
                    )
                }

            }
        }
        Row(verticalAlignment = Alignment.CenterVertically) {
            Icon(
                imageVector = Icons.Filled.Videocam,
                contentDescription = "VideoLLamada",
                tint = colorResource(R.color.white),
                modifier = Modifier.padding(end = 16.dp)
            )
            Icon(
                imageVector = Icons.Filled.Call,
                contentDescription = "LLamada",
                tint = colorResource(R.color.white),
                modifier = Modifier.padding(end = 16.dp)
            )
            Icon(
                imageVector = Icons.Filled.MoreVert,
                contentDescription = "Opciones",
                tint = colorResource(R.color.white),
                modifier = Modifier.padding(end = 16.dp)
            )
        }
    }
}


@Composable
fun EscribirMensaje(contacto: Contacto) {
    var text by rememberSaveable { mutableStateOf("") }
    Row(
        Modifier.fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        var widthMessage by remember { mutableStateOf(210.dp) }
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier
                .padding(start = 10.dp)
                .clip(CircleShape)
                .background(colorResource(R.color.mensaje))
        ) {
            Icon(
                imageVector = Icons.Filled.EmojiEmotions,
                contentDescription = "Emojis",
                tint = colorResource(R.color.iconos),
                modifier = Modifier.padding(start = 6.dp, end = 6.dp)
            )
            TextField(
                value = text,
                onValueChange = { text = it },
                placeholder = { Text("Mensaje") },
                colors = messageTextFieldColors(),
                singleLine = true,
                modifier = Modifier
                    .width(widthMessage)
                    .onKeyEvent { event ->
                        if (event.key == Key.Enter) {
                            ContactoViewModel.agregarMensaje(contacto.nombre, text)
                            ContactoViewModel.agregarMensaje(contacto, text)
                            text = ""
                            true
                        } else false}
            )
            Icon(
                imageVector = Icons.Filled.AttachFile,
                contentDescription = "Adjuntar",
                tint = colorResource(R.color.iconos),
                modifier = Modifier.padding(start = 6.dp, end = 6.dp)
            )
            if (text.isBlank()) {
                Icon(
                    imageVector = Icons.Filled.CameraAlt,
                    contentDescription = "Camara",
                    tint = colorResource(R.color.iconos),
                    modifier = Modifier.padding(end = 6.dp)
                )
                widthMessage = 210.dp
            } else {
                widthMessage = 240.dp
            }

        }
        if (text.isBlank()) {
            Button(
                onClick = {},
                shape = CircleShape,
                colors = ButtonColors(
                    colorResource(R.color.noti),
                    colorResource(R.color.black),
                    colorResource(R.color.noti),
                    colorResource(R.color.noti)
                ),
                modifier = Modifier
                    .padding(end = 16.dp)
                    .size(60.dp, 60.dp)
                    .clip(CircleShape)
            ) {
                Icon(imageVector = Icons.Filled.Mic, contentDescription = "Mic",)
            }
        } else {
            Button(
                onClick = {
                    ContactoViewModel.agregarMensaje(contacto.nombre, text)
                    ContactoViewModel.agregarMensaje(contacto, text)
                    text = ""
                },
                colors = ButtonColors(
                    colorResource(R.color.noti),
                    colorResource(R.color.black),
                    colorResource(R.color.noti),
                    colorResource(R.color.noti)
                ),
                modifier = Modifier
                    .padding(end = 16.dp)
                    .size(60.dp, 60.dp)
                    .clip(CircleShape)
            ) {
                Icon(imageVector = Icons.AutoMirrored.Filled.Send, contentDescription = "Send")
            }
        }
    }

}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun messageTextFieldColors(): TextFieldColors {
    return TextFieldDefaults.textFieldColors(
        focusedTextColor = colorResource(R.color.iconos),
        unfocusedTextColor = Color.White,
        containerColor = colorResource(R.color.mensaje),
        cursorColor = Color.White,
        unfocusedLabelColor = colorResource(R.color.iconos),
        unfocusedPlaceholderColor = colorResource(R.color.iconos)
    )
}
