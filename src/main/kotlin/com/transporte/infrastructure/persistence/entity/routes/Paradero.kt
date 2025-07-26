package com.transporte.infrastructure.persistence.entity.routes

import com.fasterxml.jackson.annotation.JsonIgnoreProperties

import jakarta.persistence.*

@Entity
@Table(name = "paradero")
@JsonIgnoreProperties(value = ["ruta"])
data class Paradero(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long = 0,

    val nombre: String,
    val latitud: Double,
    val longitud: Double,

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "ruta_id")
    @JsonIgnoreProperties("paraderos", "empresa") // evita bucles
    val ruta: Ruta
)
