package com.transporte.domain

import com.fasterxml.jackson.annotation.JsonIgnore
import com.fasterxml.jackson.annotation.JsonIgnoreProperties
import jakarta.persistence.*

@Entity
@Table(name = "empresa")
data class Empresa(
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long = 0,

    val nombre: String,

    @OneToMany(mappedBy = "empresa")
     @JsonIgnoreProperties(value = ["empresa", "hibernateLazyInitializer", "handler"])
    val rutas: List<Ruta> = mutableListOf()

)
