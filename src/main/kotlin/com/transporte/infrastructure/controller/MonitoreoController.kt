package com.transporte.infrastructure.controller

import com.transporte.application.service.MonitoreoServiceParcialSiempreInserta
import com.transporte.application.service.MonitoreoExportService
import com.transporte.application.dto.MonitoreoEventoDTO
import com.transporte.application.service.MonitoreoService
import com.transporte.shared.BaseResponse
import org.springframework.http.HttpStatus
import org.springframework.format.annotation.DateTimeFormat
import org.springframework.http.HttpHeaders
import org.springframework.http.MediaType
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import java.net.URLEncoder
import java.nio.charset.StandardCharsets
import java.time.OffsetDateTime

@RestController
@RequestMapping("/api/monitoreo")
@CrossOrigin(origins = ["*"])
class MonitoreoController(
    private val service: MonitoreoService,
    private val svc: MonitoreoServiceParcialSiempreInserta,
    private val exportService: MonitoreoExportService
) {
    @PostMapping
    fun registrar(
        @RequestBody dto: MonitoreoEventoDTO,
        @RequestHeader(value = "X-App-Version", required = false) appVersion: String?,
        @RequestHeader(value = "X-Device-Model", required = false) deviceModel: String?,
        @RequestHeader(value = "X-OS-Version", required = false) osVersion: String?
    ): ResponseEntity<BaseResponse<Map<String, Any>>> {

        val enriched = dto.copy(
            mensaje = dto.mensaje ?: if (dto.resultado == "OK") "OK" else "ERROR",
            appVersion = dto.appVersion ?: appVersion ?: "unknown",
            deviceModel = dto.deviceModel ?: deviceModel ?: "unknown",
            osVersion = dto.osVersion ?: osVersion ?: "unknown",
            sesionId = dto.sesionId?.takeIf { it.isNotBlank() } ?: java.util.UUID.randomUUID().toString()
        )

        val id = service.registrar(enriched)
        return ResponseEntity.status(HttpStatus.CREATED)
            .body(BaseResponse.ok("Evento registrado", mapOf("id" to id)))
    }

    @PostMapping("/batch")
    fun registrarBatch(
        @RequestBody dtos: List<MonitoreoEventoDTO>,
        @RequestHeader(value = "X-App-Version", required = false) appVersion: String?,
        @RequestHeader(value = "X-Device-Model", required = false) deviceModel: String?,
        @RequestHeader(value = "X-OS-Version", required = false) osVersion: String?
    ): ResponseEntity<BaseResponse<Map<String, Any>>> {

        val sessionDefault = dtos.firstOrNull { !it.sesionId.isNullOrBlank() }?.sesionId
            ?: java.util.UUID.randomUUID().toString()

        val enriched = dtos.map { dto ->
            dto.copy(
                mensaje = dto.mensaje ?: if (dto.resultado == "OK") "OK" else "ERROR",
                appVersion = dto.appVersion ?: appVersion ?: "unknown",
                deviceModel = dto.deviceModel ?: deviceModel ?: "unknown",
                osVersion = dto.osVersion ?: osVersion ?: "unknown",
                sesionId = dto.sesionId?.takeIf { it.isNotBlank() } ?: sessionDefault
            )
        }

        val count = service.registrarBatch(enriched)
        return ResponseEntity.status(HttpStatus.CREATED)
            .body(BaseResponse.ok("Eventos registrados", mapOf("count" to count)))
    }

    @PostMapping("/batch/always-insert")
    fun registrarBatchSiempreInserta(
        @RequestBody dtos: List<MonitoreoEventoDTO>,
        @RequestHeader(value = "X-App-Version", required = false) appVersion: String?,
        @RequestHeader(value = "X-Device-Model", required = false) deviceModel: String?,
        @RequestHeader(value = "X-OS-Version", required = false) osVersion: String?
    ): ResponseEntity<BaseResponse<Map<String, Any>>> {

        val sessionDefault = dtos.firstOrNull { !it.sesionId.isNullOrBlank() }?.sesionId
            ?: java.util.UUID.randomUUID().toString()

        val enriched = dtos.map { dto ->
            dto.copy(
                mensaje   = dto.mensaje ?: if (dto.resultado == "OK") "OK" else "ERROR",
                appVersion= dto.appVersion ?: appVersion ?: "unknown",
                deviceModel= dto.deviceModel ?: deviceModel ?: "unknown",
                osVersion = dto.osVersion ?: osVersion ?: "unknown",
                sesionId  = dto.sesionId?.takeIf { it.isNotBlank() } ?: sessionDefault
            )
        }

        val (okCount, hardErrors) = svc.registrarBatchSiempreInserta(enriched)
        val responseData = mapOf(
            "okCount" to okCount,
            "hardErrors" to hardErrors
        )
        val http = if (hardErrors.isEmpty()) HttpStatus.CREATED else HttpStatus.MULTI_STATUS
        return ResponseEntity.status(http)
            .body(BaseResponse.ok("Batch insertado (incluye inválidos como ERROR/INVALID)", responseData))
    }

    @GetMapping("/export.xlsx")
    fun exportarExcel(
        @RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) from: OffsetDateTime?,
        @RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) to: OffsetDateTime?,
        @RequestParam(required = false) evento: String?,
        @RequestParam(required = false) resultado: String?,
        @RequestParam(required = false) actividad: String?,
        @RequestParam(required = false) componente: String?,
        @RequestParam(required = false) usuarioId: String?,
        @RequestParam(required = false) sesionId: String?,
        @RequestParam(required = false) ingestaEstado: String?,
        @RequestParam(required = false, defaultValue = "100000") limit: Int
    ): ResponseEntity<ByteArray> {

        val bytes = exportService.exportarXlsx(
            from, to, evento, resultado, actividad, componente, usuarioId, sesionId, ingestaEstado, limit
        )

        val filename = URLEncoder.encode(
            "monitoreo_${from ?: "all"}_${to ?: "all"}.xlsx",
            StandardCharsets.UTF_8
        )

        return ResponseEntity.ok()
            .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename*=UTF-8''$filename")
            .contentType(MediaType.parseMediaType(
                "application/vnd.openxmlformats-officedocument.spreadsheetml.sheet"
            ))
            .body(bytes)
    }
}
