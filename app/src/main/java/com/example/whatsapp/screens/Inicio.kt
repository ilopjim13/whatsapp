package com.example.whatsapp.screens

import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.Call
import androidx.compose.material.icons.filled.CameraAlt
import androidx.compose.material.icons.filled.Circle
import androidx.compose.material.icons.filled.MarkUnreadChatAlt
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.filled.SupervisorAccount
import androidx.compose.material.icons.filled.Whatsapp
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.whatsapp.Contacto
import com.example.whatsapp.R
import com.example.whatsapp.navigation.AppScreen

@Composable
fun InicioScreen(navController: NavController, modifier: Modifier = Modifier) {
    InicioBody(navController, modifier)
}


@Composable
fun InicioBody(navController: NavController, modifier: Modifier = Modifier) {
    val contactos = generateContact()
    Column (modifier.fillMaxSize().background(color = colorResource(id = R.color.fondo)), verticalArrangement = Arrangement.SpaceBetween) {

        Cabecera()

        LazyColumn(Modifier.fillMaxWidth().height(755.dp)) {
            items(contactos.size) {
                Contactos(contactos[it], navController)
            }
        }

        MenuBajo()

    }

}

@Composable
fun Contactos(contacto: Contacto, navController: NavController) {
    val context = LocalContext.current
    Row(
        Modifier
            .fillMaxWidth()
            .padding(top = 8.dp, bottom = 8.dp)
            .clickable {
                //Toast.makeText(context, "No puedes entrar en el chat", Toast.LENGTH_SHORT).show()
                navController.navigate(AppScreen.ChatScreen.route)
            },
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically,
    ) {
        Row( verticalAlignment = Alignment.CenterVertically) {
            Icon(
                imageVector = Icons.Filled.AccountCircle,
                contentDescription = "Foto de perfil",
                modifier = Modifier.padding(start = 16.dp).size(48.dp),
                tint = colorResource(id = R.color.white)
            )

            Column(Modifier.padding(start = 16.dp)) {
                Text(text = contacto.nombre, color = colorResource(id = R.color.white))
                Text(text = contacto.ultimoMensaje, fontSize = 12.sp, color = colorResource(id = R.color.white))
            }
        }
        if (contacto.leido) Text(text = contacto.hora, fontSize = 11.sp, modifier = Modifier.padding(end = 16.dp), color = colorResource(id = R.color.white))
        else {
            Column {
                Text(text = contacto.hora, fontSize = 11.sp, modifier = Modifier.padding(end = 16.dp), color = colorResource(id = R.color.noti))
                Icon(
                    imageVector = Icons.Filled.Circle,
                    contentDescription = "mensajes",
                    modifier = Modifier.padding(start = 16.dp).size(16.dp),
                    tint = colorResource(id = R.color.noti)
                )
            }
        }
    }
}

@Composable
fun Cabecera() {
    Row(
        modifier = Modifier.fillMaxWidth()
            .padding(top = 10.dp)
            .drawBehind {
                val strokeWidth = 0.1.dp.toPx()
                val y = size.height - strokeWidth / 2
                drawLine(
                    color = Color.LightGray,
                    start = androidx.compose.ui.geometry.Offset(0f, y),
                    end = androidx.compose.ui.geometry.Offset(size.width, y),
                    strokeWidth = strokeWidth
                )
            },
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(text = stringResource(id = R.string.app_name), color = colorResource(id = R.color.white), fontSize = 24.sp, modifier =  Modifier.padding(start = 10.dp, bottom = 10.dp))
        Row {
            Icon(
                imageVector = Icons.Filled.CameraAlt,
                contentDescription = "Camara",
                Modifier.padding(end = 16.dp, bottom = 10.dp),
                tint = colorResource(id = R.color.white)
            )
            Icon(
                imageVector = Icons.Filled.Search,
                contentDescription = "Lupita",
                Modifier.padding(end = 16.dp, bottom = 10.dp),
                tint = colorResource(id = R.color.white)
            )
            Icon(
                imageVector = Icons.Filled.MoreVert,
                contentDescription = "Opciones",
                Modifier.padding(end = 16.dp, bottom = 10.dp),
                tint = colorResource(id = R.color.white)
            )
        }
    }
}

@Composable
fun MenuBajo() {
    Row(
        Modifier
            .fillMaxWidth()
            .drawBehind {
                val strokeWidth = 0.3.dp.toPx()
                drawLine(
                    color = Color.LightGray,
                    start = androidx.compose.ui.geometry.Offset(0f, strokeWidth / 2),
                    end = androidx.compose.ui.geometry.Offset(size.width, strokeWidth / 2),
                    strokeWidth = strokeWidth
                )
            },
        horizontalArrangement = Arrangement.SpaceEvenly) {
        Column(horizontalAlignment = Alignment.CenterHorizontally, modifier = Modifier.padding(top = 8.dp, bottom = 8.dp)) {
            Icon(
                imageVector = Icons.Filled.MarkUnreadChatAlt,
                contentDescription = "Chats",
                tint = colorResource(id = R.color.noti),
            )
            Text(text = stringResource(id = R.string.chats), color = colorResource(id = R.color.white))
        }
        Column(horizontalAlignment = Alignment.CenterHorizontally, modifier = Modifier.padding(top = 8.dp, bottom = 8.dp)) {
            Icon(
                imageVector = Icons.Filled.Whatsapp,
                contentDescription = "Novedades",
                tint = colorResource(id = R.color.white)
            )
            Text(text = stringResource(id = R.string.novedades), color = colorResource(id = R.color.white))
        }
        Column(horizontalAlignment = Alignment.CenterHorizontally, modifier = Modifier.padding(top = 8.dp, bottom = 8.dp)) {
            Icon(
                imageVector = Icons.Filled.SupervisorAccount,
                contentDescription = "Comunidades",
                tint = colorResource(id = R.color.white)
            )
            Text(text = stringResource(id = R.string.comunidades), color = colorResource(id = R.color.white))
        }
        Column(horizontalAlignment = Alignment.CenterHorizontally, modifier = Modifier.padding(top = 8.dp, bottom = 8.dp)) {
            Icon(
                imageVector = Icons.Filled.Call,
                contentDescription = "Llamadas",
                tint = colorResource(id = R.color.white)
            )
            Text(text = stringResource(id = R.string.llamadas), color = colorResource(id = R.color.white))
        }
    }
}

//@Preview(showBackground = true)
//@Composable
//fun WhatsappPreview() {
//    WhatsappTheme {
//        InicioBody()
//    }
//}

fun generateContact() :List<Contacto> {
    return listOf(
        Contacto("Nyx", "hehe", "18:14", ""),
        Contacto("Pepe", "adiooh", "17:37", "", false),
        Contacto("Paco", "no", "17:35", ""),
        Contacto("Luis", "Nueva tarea", "17:20", ""),
        Contacto("Jose", "mañanaa", "17:05", ""),
        Contacto("Laura", "q va", "17:02", ""),
        Contacto("Jose Manuel", "q tal picha?", "16:58", "", false),
        Contacto("Nicolas", "estoy en el baño", "16:51", ""),
        Contacto("Paula", "noce", "16:33", ""),
        Contacto("Manolo", "ji o q?", "16:22", ""),
        Contacto("Roberto", "vale", "16:12", ""),
        Contacto("Pablo", "nos vemos mañana bro", "16:03", "", false),
        Contacto("Alfonchiriri", "un tarkov?", "16:00", ""),
        Contacto("Adri", "tengo wifi espacial", "13:50", "", false),
        Contacto("Aaron", "me he visto one piece de nuevo", "", "13:37"),
        Contacto("Elias", "como se enciende el horno?", "", "13:18"),
        Contacto("Julio", "gym?", "12:03", "", false)
    )

}