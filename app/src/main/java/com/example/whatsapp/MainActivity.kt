package com.example.whatsapp

import android.os.Bundle
import android.text.Layout
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.camera.core.Camera
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.gestures.ScrollableState
import androidx.compose.foundation.gestures.scrollable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.Call
import androidx.compose.material.icons.filled.CameraAlt
import androidx.compose.material.icons.filled.Chat
import androidx.compose.material.icons.filled.MarkUnreadChatAlt
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.filled.SupervisorAccount
import androidx.compose.material.icons.filled.Whatsapp
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.whatsapp.ui.theme.WhatsappTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            WhatsappTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    Whatsapp(
                        modifier = Modifier.padding(innerPadding)
                    )
                }
            }
        }
    }
}

@Composable
fun Whatsapp(modifier: Modifier = Modifier) {
    val contactos = listOf(Contacto("Pepe", "adiooh", "17:37"), Contacto("Paco", "no", "17:35"), Contacto("Luis", "Nueva tarea", "17:20"), Contacto("Jose", "mañanaa", "17:05"), Contacto("Laura", "q va", "17:02"), Contacto("Jose Manuel", "q tal picha?", "16:58"), Contacto("Nicolas", "estoy en el baño", "16:51"), Contacto("Nyx", "hehe", "16:47"), Contacto("Paula", "noce", "16:33"), Contacto("Manolo", "ji o q?", "16:22"), Contacto("Roberto", "vale", "16:12"), Contacto("Pablo", "nos vemos mañana bro", "16:03"), Contacto("Alfonchiriri", "un tarkov?", "16:00"), Contacto("Adri", "tengo wifi espacial", "13:50"), Contacto("Aaron", "me he visto one piece de nuevo", "13:37"), Contacto("Elias", "como se enciende el horno?", "13:18"), Contacto("Julio", "gym?", "12:03"))
    Column (modifier.fillMaxSize().background(color = Color(16, 29, 37, 255)), verticalArrangement = Arrangement.SpaceBetween) {

        Cabecera(modifier)

        LazyColumn(Modifier.fillMaxWidth().height(741.dp)) {
            items(contactos.size) {
                Contactos(contactos[it])
            }
        }

        MenuBajo()

    }

}

@Composable
fun Contactos(contacto: Contacto) {
    val context = LocalContext.current
    Row(
        Modifier
            .fillMaxWidth()
            .padding(top = 16.dp)
            .clickable {
                Toast.makeText(context, "Vas a entrar a un chat", Toast.LENGTH_SHORT).show()
            },
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically,
    ) {
        Row( verticalAlignment = Alignment.CenterVertically) {
            Icon(
                imageVector = Icons.Filled.AccountCircle,
                contentDescription = "Foto de perfil",
                modifier = Modifier.padding(start = 16.dp),
                tint = Color.White
            )

            Column(Modifier.padding(start = 16.dp)) {
                Text(text = contacto.nombre, color = Color.White)
                Text(text = contacto.ultimoMensaje, fontSize = 12.sp, color = Color.White)
            }
        }

        Text(text = contacto.hora, fontSize = 11.sp, modifier = Modifier.padding(end = 16.dp), color = Color.White)
    }
}

@Composable
fun Cabecera(modifier: Modifier) {
    Row(
        modifier = modifier.fillMaxWidth()
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
        Text(text = "Whatsapp", color = Color.White, fontSize = 24.sp, modifier =  modifier.padding(start = 10.dp, bottom = 10.dp))
        Row {
            Icon(
                imageVector = Icons.Filled.CameraAlt,
                contentDescription = "Camara",
                modifier.padding(end = 16.dp, bottom = 10.dp),
                tint = Color.White
            )
            Icon(
                imageVector = Icons.Filled.Search,
                contentDescription = "Lupita",
                modifier.padding(end = 16.dp, bottom = 10.dp),
                tint = Color.White
            )
            Icon(
                imageVector = Icons.Filled.MoreVert,
                contentDescription = "Opciones",
                modifier.padding(end = 16.dp, bottom = 10.dp),
                tint = Color.White
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
                tint = Color.White,
            )
            Text(text = "Chats", color = Color.White)
        }
        Column(horizontalAlignment = Alignment.CenterHorizontally, modifier = Modifier.padding(top = 8.dp, bottom = 8.dp)) {
            Icon(
                imageVector = Icons.Filled.Whatsapp,
                contentDescription = "Novedades",
                tint = Color.White
            )
            Text(text = "Novedades", color = Color.White)
        }
        Column(horizontalAlignment = Alignment.CenterHorizontally, modifier = Modifier.padding(top = 8.dp, bottom = 8.dp)) {
            Icon(
                imageVector = Icons.Filled.SupervisorAccount,
                contentDescription = "Comunidades",
                tint = Color.White
            )
            Text(text = "Comunidades", color = Color.White)
        }
        Column(horizontalAlignment = Alignment.CenterHorizontally, modifier = Modifier.padding(top = 8.dp, bottom = 8.dp)) {
            Icon(
                imageVector = Icons.Filled.Call,
                contentDescription = "Llamadas",
                tint = Color.White
            )
            Text(text = "Llamadas", color = Color.White)
        }
    }
}

@Preview(showBackground = true)
@Composable
fun WhatsappPreview() {
    WhatsappTheme {
        Whatsapp()
    }
}