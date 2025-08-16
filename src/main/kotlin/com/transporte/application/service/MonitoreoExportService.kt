package com.transporte.application.service

import com.fasterxml.jackson.module.kotlin.jacksonObjectMapper
import com.transporte.infrastructure.persistence.entity.monitoring.MonitoreoEventoEntity
import com.transporte.infrastructure.repository.MonitoreoEventoRepository
import com.transporte.infrastructure.persistence.spec.MonitoreoSpecs
import org.apache.poi.ss.usermodel.FillPatternType
import org.apache.poi.ss.usermodel.HorizontalAlignment
import org.apache.poi.xssf.usermodel.XSSFWorkbook
import org.springframework.data.domain.Sort
import org.springframework.data.jpa.domain.Specification
import org.springframework.stereotype.Service
import java.io.ByteArrayOutputStream
import java.time.OffsetDateTime

@Service
class MonitoreoExportService(
    private val repo: MonitoreoEventoRepository
) {
    private val json = jacksonObjectMapper()

    fun exportarXlsx(
        from: OffsetDateTime?,
        to: OffsetDateTime?,
        evento: String?,
        resultado: String?,
        actividad: String?,
        componente: String?,
        usuarioId: String?,
        sesionId: String?,
        ingestaEstado: String?,
        limit: Int = 100_000
    ): ByteArray {
        // Build spec
        var spec: Specification<MonitoreoEventoEntity> = Specification.where(null)
        spec = spec.and(MonitoreoSpecs.between(from, to))
            .and(MonitoreoSpecs.eq("evento", evento))
            .and(MonitoreoSpecs.eq("resultado", resultado))
            .and(MonitoreoSpecs.like("actividad", actividad))
            .and(MonitoreoSpecs.like("componente", componente))
            .and(MonitoreoSpecs.eq("usuarioId", usuarioId))
            .and(MonitoreoSpecs.eq("sesionId", sesionId))
            .and(MonitoreoSpecs.ingestaEstado(ingestaEstado))

        val data = repo.findAll(spec, Sort.by(Sort.Direction.DESC, "creadoEn"))
            .take(limit)

        // Excel
        val wb = XSSFWorkbook()
        val sh = wb.createSheet("Monitoreo")
        var rowIdx = 0

        val header = listOf(
            "id","creado_en","usuario_id","sesion_id",
            "evento","resultado","mensaje",
            "actividad","componente","referencia",
            "app_version","device_model","os_version",
            "lat","lng","accuracy_m",
            "ingesta_estado","ingesta_error",
            "detalles_json","raw_payload_json"
        )

        // estilos mínimos
        val headerStyle = wb.createCellStyle().apply {
            alignment = HorizontalAlignment.CENTER
            fillPattern = FillPatternType.SOLID_FOREGROUND
            fillForegroundColor = 22 // gris claro
        }

        // header
        sh.createRow(rowIdx++).also { r ->
            header.forEachIndexed { i, h ->
                val c = r.createCell(i)
                c.setCellValue(h)
                c.cellStyle = headerStyle
            }
        }

        // filas
        data.forEach { e ->
            sh.createRow(rowIdx++).apply {
                var col = 0
                fun set(v: Any?) { this.createCell(col++).setCellValue(v?.toString() ?: "") }

                set(e.id)
                set(e.creadoEn)
                set(e.usuarioId); set(e.sesionId)
                set(e.evento); set(e.resultado); set(e.mensaje)
                set(e.actividad); set(e.componente); set(e.referencia)
                set(e.appVersion); set(e.deviceModel); set(e.osVersion)
                set(e.lat); set(e.lng); set(e.accuracyM)
                set(e.ingestaEstado); set(e.ingestaError)
                set(e.detalles?.let { json.writeValueAsString(it) })
                set(e.rawPayload?.let { json.writeValueAsString(it) })
            }
        }

        (0 until header.size).forEach { sh.autoSizeColumn(it) }

        return ByteArrayOutputStream().use { out ->
            wb.write(out); wb.close(); out.toByteArray()
        }
    }
}
