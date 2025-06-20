package com.transporte.domain

import com.fasterxml.jackson.annotation.JsonIgnore
import jakarta.persistence.*

@Entity
@Table(name = "paradero")
data class Paradero(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long = 0,

    val nombre: String,
    val latitud: Double,
    val longitud: Double,

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "ruta_id")
    @JsonIgnore  // evita que la respuesta JSON devuelva la ruta dentro del paradero
    val ruta: Ruta
)
