package com.transporte.infrastructure.persistence.entity.routes

import jakarta.persistence.*

@Entity
@Table(name="bus")
data class Bus(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long = 0,  // Cambiar a nullable
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "ruta_id")
    val ruta: Ruta,
    val latitud: Double,
    val longitud: Double,
    val timestamp: Long
)
