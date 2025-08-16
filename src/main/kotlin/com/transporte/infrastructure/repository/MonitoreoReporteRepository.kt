package com.transporte.infrastructure.repository

import com.transporte.infrastructure.persistence.entity.monitoring.MonitoreoEventoEntity
import org.springframework.data.jpa.repository.Query
import org.springframework.data.repository.Repository
import java.time.OffsetDateTime

interface MonitoreoReporteRepository : Repository<MonitoreoEventoEntity, Long> {

    @Query(
        value = """
        WITH base AS (
            SELECT actividad, componente, resultado, ingesta_estado
            FROM monitoreo_evento
            WHERE (:from IS NULL OR creado_en >= :from)
              AND (:to   IS NULL OR creado_en <= :to)
        )
        SELECT
            actividad,
            componente,
            COUNT(*)::bigint                               AS total,
            SUM( (resultado = 'ERROR')::int )::bigint      AS erroresApp,
            SUM( (ingesta_estado = 'INVALID')::int )::bigint AS invalidosIngesta,
            ROUND(100.0 * SUM((resultado='ERROR')::int) / NULLIF(COUNT(*),0), 2) AS pctError,
            ROUND(100.0 * SUM((ingesta_estado='INVALID')::int)/NULLIF(COUNT(*),0),2) AS pctInvalid
        FROM base
        GROUP BY actividad, componente
        ORDER BY erroresApp DESC NULLS LAST, invalidosIngesta DESC NULLS LAST
        LIMIT :limit
        """,
        nativeQuery = true
    )
    fun topErrores(
        from: OffsetDateTime?,
        to: OffsetDateTime?,
        limit: Int
    ): List<Array<Any?>>
}
