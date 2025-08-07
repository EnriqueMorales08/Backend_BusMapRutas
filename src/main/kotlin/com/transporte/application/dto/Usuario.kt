package com.transporte.application.dto

import java.time.LocalDateTime

data class Usuario(
    val id: Long,
    val dni: String,
    val nombre: String,
    val correo: String,
    val celular: String,
    val estado: Boolean,
    val fotoPerfil: String,
    val dniPosterior: String,
    val dniFrontal: String,
    val password: String,
    val fechaRegistro: LocalDateTime,
    val fechaValidacion: LocalDateTime?
)
