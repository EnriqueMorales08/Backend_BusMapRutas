package com.transporte.infrastructure.controller

import com.transporte.application.InformacionService
import com.transporte.infrastructure.dto.InformacionDto
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/api/informacion")
class InformacionController(
    private val informacionService: InformacionService
) {

    @GetMapping("/{empresaId}")
    fun obtenerInformacion(@PathVariable empresaId: Long): InformacionDto? {
        return informacionService.obtenerInformacionPorEmpresa(empresaId)
    }
}
