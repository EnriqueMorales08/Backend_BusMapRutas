package com.transporte.domain

import jakarta.persistence.*
import java.time.LocalTime

@Entity
@Table(name = "informacion")
data class Informacion(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long = 0,

    @Column(name = "numero_unidades")
    val numeroUnidades: Int,

    @Column(name = "duracion_recorrido")
    val duracionRecorrido: String,

    @Column(name = "longitud_recorrido")
    val longitudRecorrido: Double,

    @Column(name = "inicio_servicio_lunes_viernes")
    val inicioServicioLunesViernes: String,

    @Column(name = "inicio_servicio_sabado")
    val inicioServicioSabado: String,

    @Column(name = "inicio_servicio_domingo")
    val inicioServicioDomingo: String,

    @Column(name = "fin_servicio_lunes_viernes")
    val finServicioLunesViernes: String,

    @Column(name = "fin_servicio_sabado")
    val finServicioSabado: String,

    @Column(name = "fin_servicio_domingo")
    val finServicioDomingo: String,

    @Column(name = "mensaje")
    val mensaje: String,

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "empresa_id", referencedColumnName = "id")
    val empresa: Empresa
)

