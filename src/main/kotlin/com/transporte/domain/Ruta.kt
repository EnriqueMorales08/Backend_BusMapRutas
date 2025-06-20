package com.transporte.domain


import com.fasterxml.jackson.annotation.JsonIgnoreProperties
import jakarta.persistence.*

@Entity
@Table(name = "rutas")
data class Ruta(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long = 0,

    val nombre: String,

    @OneToMany(mappedBy = "ruta", cascade = [CascadeType.ALL], orphanRemoval = true, fetch = FetchType.LAZY)
    @JsonIgnoreProperties("ruta")  // evita recursión infinita al serializar
    val paraderos: List<Paradero> = mutableListOf()
)
