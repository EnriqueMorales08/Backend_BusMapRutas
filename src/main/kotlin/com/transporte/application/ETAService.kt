package com.transporte.service

import com.transporte.domain.Bus
import com.transporte.domain.Paradero
import com.transporte.infrastructure.repository.BusRepository
import com.transporte.infrastructure.repository.ParaderoRepository
import org.springframework.stereotype.Service
import kotlin.math.*

@Service
class ETAService(
    private val busRepository: BusRepository,
    private val paraderoRepository: ParaderoRepository
) {
    fun calcularETA(busId: Long, usuarioLat: Double, usuarioLng: Double, rutaId: Long): Double {
        val bus: Bus = busRepository.findById(busId).orElseThrow()

        val paraderoCercano: Paradero = paraderoRepository
            .findParaderosOrdenadosPorDistancia(usuarioLat, usuarioLng, rutaId)
            .firstOrNull() ?: throw RuntimeException("No se encontraron paraderos")

        return estimarTiempoLlegada(
            bus.latitud,
            bus.longitud,
            paraderoCercano.latitud,
            paraderoCercano.longitud
        )
    }

    private fun estimarTiempoLlegada(
        lat1: Double, lon1: Double,
        lat2: Double, lon2: Double
    ): Double {
        val R = 6371 // Radio de la Tierra en km
        val dLat = Math.toRadians(lat2 - lat1)
        val dLon = Math.toRadians(lon2 - lon1)
        val a = sin(dLat / 2).pow(2.0) + cos(Math.toRadians(lat1)) * cos(Math.toRadians(lat2)) * sin(dLon / 2).pow(2.0)
        val c = 2 * atan2(sqrt(a), sqrt(1 - a))
        val distanciaKm = R * c

        val velocidadPromedioKmPorHora = 30.0
        val tiempoHoras = distanciaKm / velocidadPromedioKmPorHora
        return tiempoHoras * 60 // minutos
    }
}
