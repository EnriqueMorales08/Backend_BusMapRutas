package com.transporte.infrastructure.persistence.spec

import com.transporte.infrastructure.persistence.entity.monitoring.MonitoreoEventoEntity
import org.springframework.data.jpa.domain.Specification
import java.time.OffsetDateTime

object MonitoreoSpecs {
    fun between(from: OffsetDateTime?, to: OffsetDateTime?) =
        Specification<MonitoreoEventoEntity> { root, _, cb ->
            when {
                from != null && to != null -> cb.between(root.get("creadoEn"), from, to)
                from != null -> cb.greaterThanOrEqualTo(root.get("creadoEn"), from)
                to   != null -> cb.lessThanOrEqualTo(root.get("creadoEn"), to)
                else -> cb.conjunction()
            }
        }

    fun eq(field: String, value: String?) =
        Specification<MonitoreoEventoEntity> { root, _, cb ->
            if (value.isNullOrBlank()) cb.conjunction()
            else cb.equal(root.get<String>(field), value)
        }

    fun like(field: String, value: String?) =
        Specification<MonitoreoEventoEntity> { root, _, cb ->
            if (value.isNullOrBlank()) cb.conjunction()
            else cb.like(cb.lower(root.get(field)), "%${value.lowercase()}%")
        }

    fun ingestaEstado(value: String?) =
        eq("ingestaEstado", value)
}
