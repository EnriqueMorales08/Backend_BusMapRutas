package com.transporte.infrastructure.persistence.entity.routes


import com.fasterxml.jackson.annotation.JsonIgnoreProperties
import jakarta.persistence.*
@Entity
@Table(name = "rutas")
data class Ruta(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long = 0,

    val nombre: String,

   @ManyToOne(fetch = FetchType.LAZY)
   @JoinColumn(name = "empresa_id")
   @JsonIgnoreProperties(value = ["rutas", "hibernateLazyInitializer", "handler"])
   val empresa: Empresa,

    @OneToMany(mappedBy = "ruta", cascade = [CascadeType.ALL], orphanRemoval = true, fetch = FetchType.LAZY)
    @JsonIgnoreProperties("ruta")
    val paraderos: List<Paradero> = mutableListOf()
)
