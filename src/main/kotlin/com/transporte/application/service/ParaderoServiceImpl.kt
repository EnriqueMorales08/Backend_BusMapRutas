package com.transporte.application.service

import com.transporte.application.service.ParaderoService
import com.transporte.infrastructure.persistence.entity.routes.Paradero
import com.transporte.domain.ports.output.ParaderoRepository
import org.springframework.stereotype.Service

@Service
class ParaderoServiceImpl(
    private val paraderoRepository: ParaderoRepository
) : ParaderoService {
    override fun obtenerParaderoMasCercano(lat: Double, lng: Double, rutaId: Long): Paradero {
        val paraderos = paraderoRepository.findParaderosOrdenadosPorDistancia(lat, lng, rutaId)
        return paraderos.firstOrNull()
            ?: throw RuntimeException("No se encontraron paraderos cercanos")
    }
}
