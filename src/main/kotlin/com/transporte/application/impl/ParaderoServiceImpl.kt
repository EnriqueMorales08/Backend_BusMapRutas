package com.transporte.application.impl

import com.transporte.application.ParaderoService
import com.transporte.domain.Paradero
import com.transporte.infrastructure.repository.ParaderoRepository
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
