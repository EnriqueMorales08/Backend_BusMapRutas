package com.transporte.domain
import com.fasterxml.jackson.annotation.JsonIgnore

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
    @JsonIgnore
    val ruta: Ruta
)
