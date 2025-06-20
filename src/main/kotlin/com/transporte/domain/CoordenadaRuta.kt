package com.transporte.domain

import jakarta.persistence.*

@Entity
@Table(name = "coordenadas_ruta")
data class CoordenadaRuta(
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long = 0,

    val latitud: Double,
    val longitud: Double,
    val orden: Int,

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "ruta_id")
    val ruta: Ruta
)
