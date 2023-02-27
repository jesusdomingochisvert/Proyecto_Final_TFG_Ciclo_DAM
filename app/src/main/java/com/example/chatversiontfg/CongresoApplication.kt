package com.example.chatversiontfg

import android.app.Application
import com.example.chatversiontfg.common.database.CongresoAPI
import com.example.chatversiontfg.common.entities.*
import java.util.Stack

class CongresoApplication : Application() {

    companion object {

        // Declaramos la variable de la API como variable global en todo el proyecto.

        lateinit var congresoAPI: CongresoAPI
        lateinit var asistente : AsistenteEntity
        lateinit var restaurante : RestauranteEntity
        var idEvento: Long? = 0L
        lateinit var bonos: MutableList<BonoEntity>

        var socio : Boolean = false
        var recientes = Stack<EventoEntity>()
        var eventosLike = mutableListOf<EventoEntity>()
        var restauranteLike = mutableListOf<RestauranteEntity>()

        lateinit var email: String
        lateinit var pwd: String
        lateinit var socioId: String
        lateinit var nombreChat: String
        lateinit var imagenChat: String
        var status = false

    }

    override fun onCreate() {

        super.onCreate()

        // Iniciamos API

        congresoAPI = CongresoAPI(this)

    }

}