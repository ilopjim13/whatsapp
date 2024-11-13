package com.example.whatsapp

import android.graphics.Color
import android.os.Bundle
import android.text.Layout
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.background
import androidx.compose.foundation.gestures.ScrollableState
import androidx.compose.foundation.gestures.scrollable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
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
    Column (modifier.fillMaxSize().background(color = Color.rgb(7, 94, 84)), verticalArrangement = Arrangement.SpaceBetween) {
        Row(modifier.fillMaxWidth().padding(top = 10.dp, start = 10.dp)) {
            Text(text = "Whatsapp", fontSize = 24.sp)
        }

        Column(Modifier.fillMaxWidth()) {
            Contactos()
            Contactos()
            Contactos()
            Contactos()
            Contactos()
            Contactos()
            Contactos()
            Contactos()
            Contactos()
            Contactos()
            Contactos()
            Contactos()
            Contactos()
            Contactos()
            Contactos()
            Contactos()
        }


        Row(modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceEvenly) {
            Box(modifier) {
                Text(text = "Chats")
            }
            Box(modifier) {
                Text(text = "Novedades")
            }
            Box(modifier) {
                Text(text = "Comunidades")
            }
            Box(modifier) {
                Text(text = "Llamadas")
            }
        }



    }

}

@Composable
fun Contactos() {
    Row(Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceEvenly, verticalAlignment = Alignment.CenterVertically) {
        Text(text = "Foto")
        Column {
            Text(text = "Nombre del contacto")
            Text(text = "Ultimo mensaje...", fontSize = 12.sp)
        }
        Text(text = "21:24", fontSize = 11.sp)
    }
}

@Preview(showBackground = true)
@Composable
fun WhatsappPreview() {
    WhatsappTheme {
        Whatsapp()
    }
}