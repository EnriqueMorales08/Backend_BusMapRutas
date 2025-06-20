package com.transporte.persistence

import jakarta.persistence.Entity
import jakarta.persistence.Id
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType


@Entity
data class UsuarioEntity(
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long = 0,
    val nombre: String,
    val correo: String,
    val celular: String,
    val password: String,
    val dni: String,
    val fotoPerfil: String,
    val dniFrontal: String,
    val dniPosterior: String,
    val estado: Boolean = false
)
