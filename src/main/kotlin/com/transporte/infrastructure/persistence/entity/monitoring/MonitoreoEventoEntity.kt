package com.transporte.infrastructure.persistence.entity.monitoring

import jakarta.persistence.*
import org.hibernate.annotations.JdbcTypeCode
import org.hibernate.type.SqlTypes
import java.time.OffsetDateTime

@Entity
@Table(name = "monitoreo_evento")
data class MonitoreoEventoEntity(
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long? = null,

    val evento: String,
    val resultado: String,
    val mensaje: String? = null,

    val actividad: String? = null,
    val componente: String? = null,
    val referencia: String? = null,

    @JdbcTypeCode(SqlTypes.JSON)
    @Column(columnDefinition = "jsonb")
    val detalles: Map<String, Any?>? = null,

    @Column(name = "usuario_id")
    val usuarioId: String? = null,
    @Column(name = "sesion_id")
    val sesionId: String? = null,

    @Column(name = "app_version")
    val appVersion: String? = null,
    @Column(name = "device_model")
    val deviceModel: String? = null,
    @Column(name = "os_version")
    val osVersion: String? = null,

    val lat: Double? = null,
    val lng: Double? = null,
    @Column(name = "accuracy_m")
    val accuracyM: Double? = null,

    @Column(name = "ingesta_estado")
    val ingestaEstado: String? = null,
    @Column(name = "ingesta_error")
    val ingestaError: String? = null,

    @JdbcTypeCode(SqlTypes.JSON)
    @Column(name = "raw_payload", columnDefinition = "jsonb")
    val rawPayload: Map<String, Any?>? = null,

    @Column(name = "creado_en")
    val creadoEn: OffsetDateTime = OffsetDateTime.now()
)
