package com.example.whatsapp

import android.annotation.SuppressLint
import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.runtime.mutableStateListOf
import androidx.lifecycle.ViewModel
import java.time.LocalTime

object ContactoViewModel:ViewModel() {
    var contactos = mutableStateListOf(
        Contacto("Nyx", "18:14", "", true, false,false, mutableStateListOf("Acabo de aprender 50 recetas sin gluten que puedes comer con los ojos cerrados mientras hacia un tatuaje.", "ci", "me encantaria verlo", "hehe"), mutableStateListOf("boooof, cuando quieras las hacemos", "boooof, yo acabo de hacer la interfaz del whatsap en android studiod xd", "mañana si eso")),
        Contacto("Pepe", "17:35","Hoy a las 17:35", false, false, true, mutableStateListOf("K de tiempo surmanooo", "q tal supichaa", "de puta madree lokoo", "po bueno venga nos vemoo", "adiooo"), mutableStateListOf("ya ve broo", "bien y tu q", "me alegroo", "venga bro")),
        Contacto("Paco", "17:35", "", true, true, false, mutableStateListOf("Illoooo", "me das 5 euros"), mutableStateListOf("q quieres", "no")),
        Contacto("Luis", "17:20", "", true, false, false, mutableStateListOf("Nueva tarea")),
        Contacto("Jose", "17:05", "Online", true, false, true, mutableStateListOf("", "", "mañana", "ci"), mutableStateListOf("Illo tengo una pregunta importante", "que dia habia que entregar lo de pmdm", "no jodas tamos jodidos")),
        Contacto("Laura", "17:02", "", true, false, false, mutableStateListOf("Como se llama el anime del chaval que no tiene poderes en un mundo de magia, y quiere ser el rey mago", "gracias cuantos capitulos tenia", "", "gracias me lo empezaré mañana"), mutableStateListOf("black clover, es la polla", "170", "Es largo pero merece la pena")),
        Contacto("Jose Manuel", "16:58", "", false, false, false, mutableStateListOf("illo mano me pasas la tarea", "como se hace el ejercicio 1", "bro", "bro", "bro", "te acuerdas de lo que dijeron hace 5 minutos"), mutableStateListOf("", "", "", "", "")),
        Contacto("Nicolas", "16:51", "", true, false, false, mutableStateListOf("como se tabula en kotlin", "está mu dificil", "mandamelo mejor", "luego te los doy que estoy cagando"), mutableStateListOf("con el tab bro", "", "5 euros")),
        Contacto("Suyen", "16:33", "", true, false, false, mutableStateListOf("Me cae muy bien Nyx", "Cuando querais quedamos otro dia", "Esta vez aqui", "Aqui os espero"), mutableStateListOf("Normal es increible", "", "Venga perfe cuando quieras")),
        Contacto("Manolo", "16:22", "Hoy a las 16:22", true, false, true, mutableStateListOf("Cuchamee", "a las 5 en los patos o q", "llevate el bombito surmanoo", "jujujuu"), mutableStateListOf("", "enga bro alli nos vemos", "claro q si broo amo a rompee to los parii")),
        Contacto("Roberto", "16:12", "Hoy a las 16:12", false, false, true, mutableStateListOf("Hijo voy a por tabaco para no volver, pero antes te pillo los 20 euros de tu ucha venga", "ah por cierto eres adoptado por si no lo sabias", "sisi broma, pero que seas chino y nosotros no no te habia llamao la atencion o q, ademas de adoptao es gilipollas el niño", "ahora si que me voy", "uwu"), mutableStateListOf("", "gracias papa, pero ya la misma broma no hace gracia", "", "venga perfe")),
        Contacto("Pablo", "16:03", "", false, true, false, mutableStateListOf("nos vemos mañana bro"), mutableStateListOf("no")),
        Contacto("Alfonchiriri", "16:00", "", true, false,false, mutableStateListOf("", "callate furro tu", "booof de una", "si soy buenisimo bro de que me hablas", "nove como llora el notaa", "deja la lloreria"), mutableStateListOf("ostia un furro no ve q asco", "cuchame bro, esta noche tarkov va", "pero no vayas a manquear como siempre", "de que siempre tengo que salvar tus cosas, malooo", "")),
        Contacto("Adri", "13:50", "", false, false, false, mutableStateListOf("tengo wifi espacial", "que me he comprao el wifi del elon musk", "como lo sabes hermano..."), mutableStateListOf("q dice ahora bro", "No jodaaass, ya no mas wifi palmar eso es un milagro")),
        Contacto( "Aaron", "13:37", "", true, false, false, mutableStateListOf("Qué has hecho hoy", "Acabo de empezarme a ver One Piece.", "me he visto one piece de nuevo..."), mutableStateListOf("Nada y tu","Genial espero que te guste.") ),
        Contacto( "Elias", "13:18", "", true, false, false, mutableStateListOf("Sabes usar el horno", "No, cómo se enciende", "como se enciende el horno"), mutableStateListOf("Es fácil, solo sigue las instrucciones.") ),
        Contacto("Julio", "12:03", "", false, false, false, mutableStateListOf("estoy malo y no puedo programar", "de una mañana me levanto a las 7 y vamos", "bueno y el gym entoces..."), mutableStateListOf("me la pela, mañana gym o q", "enga de una como siempre"))
    )


    fun marcarComoLeido(contacto: Contacto) {
        contacto.leido= true
    }

    fun agregarMensaje(contacto: Contacto, mensaje:String) {
        contacto.mensajesRecibidos.add(mensaje)
    }


    @SuppressLint("NewApi")
    fun agregarMensaje(nombre: String, mensaje: String)
    {
        val index = contactos.indexOfFirst { it.nombre == nombre }
        if (index != -1) {
            contactos[index].mensajesRecibidos.add(mensaje)
            marcarComoRespondido(contactos[index])
            contactos[index].hora = obtenerHoraMinutos()
            contactos.add(0, contactos[index])
            contactos.removeAt(index + 1)
        }
    }


    private fun marcarComoRespondido(contacto: Contacto) {
        contacto.responder = true
    }

    fun escribirUltimoMensaje(contacto: Contacto):String {
        return if (contacto.mensajesRecibidos.size >= contacto.mensajesEnviados.size) contacto.mensajesRecibidos.last() else contacto.mensajesEnviados.last()
    }

    @SuppressLint("DefaultLocale")
    @RequiresApi(Build.VERSION_CODES.O)
    fun obtenerHoraMinutos(): String {
        val ahora = LocalTime.now()
        val hora = ahora.hour
        val minutos = ahora.minute
        return String.format("%02d:%02d", hora, minutos)
    }





}