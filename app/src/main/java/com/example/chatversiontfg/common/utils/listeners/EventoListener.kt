package com.example.chatversiontfg.common.utils.listeners

import com.example.chatversiontfg.common.entities.EventoEntity

interface EventoListener {
    fun onClickEvento(eventoEntity: EventoEntity)
}