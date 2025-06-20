package com.transporte.infrastructure.controller

import com.transporte.service.ETAService
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/api/eta")
class ETAController(private val etaService: ETAService) {

    @GetMapping
    fun calcularETA(
        @RequestParam busId: Long,
        @RequestParam usuarioLat: Double,
        @RequestParam usuarioLng: Double,
        @RequestParam rutaId: Long
    ): Double {
        return etaService.calcularETA(busId, usuarioLat, usuarioLng,rutaId)
    }
}
