package com.transporte.infrastructure.controller

import com.transporte.application.service.ParaderoService
import com.transporte.infrastructure.persistence.entity.routes.Paradero
import com.transporte.application.dto.ParaderoDto

import org.springframework.web.bind.annotation.*



@RestController
@RequestMapping("/api/paraderos")
class ParaderoController(
    private val paraderoService: ParaderoService
) {

   @GetMapping("/cercano")
    fun obtenerParaderoMasCercano(
        @RequestParam lat: Double,
        @RequestParam lng: Double,
        @RequestParam rutaId: Long
    ): ParaderoDto {
        val p = paraderoService.obtenerParaderoMasCercano(lat, lng, rutaId)
        return ParaderoDto(p.id, p.nombre, p.latitud, p.longitud)
    }
}
