package com.transporte.infrastructure.controller

import com.transporte.application.service.MonitoreoReporteService
import org.springframework.format.annotation.DateTimeFormat
import org.springframework.http.HttpHeaders
import org.springframework.http.MediaType
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import java.net.URLEncoder
import java.nio.charset.StandardCharsets
import java.time.OffsetDateTime

@RestController
@RequestMapping("/api/monitoreo/reportes")
class MonitoreoReporteController(
    private val service: MonitoreoReporteService
) {
    @GetMapping("/top-errores.xlsx")
    fun topErroresXlsx(
        @RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) from: OffsetDateTime?,
        @RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) to: OffsetDateTime?,
        @RequestParam(required = false, defaultValue = "50") limit: Int
    ): ResponseEntity<ByteArray> {
        val bytes = service.topErroresXlsx(from, to, limit)
        val name = URLEncoder.encode("top_errores_${from ?: "all"}_${to ?: "all"}.xlsx", StandardCharsets.UTF_8)
        return ResponseEntity.ok()
            .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename*=UTF-8''$name")
            .contentType(MediaType.parseMediaType("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet"))
            .body(bytes)
    }
}
