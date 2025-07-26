package com.transporte.domain.model

data class Usuario(
    val id: Long? = null,
    val nombre: String,
    val correo: String,
    val celular: String,
    val password: String,
    val dni: String,
    val fotoPerfil: String,
    val dniFrontal: String,
    val dniPosterior: String,
    val estado: Boolean
)
