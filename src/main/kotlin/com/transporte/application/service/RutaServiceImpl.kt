package com.transporte.application.service

import com.transporte.application.service.RutaService
import com.transporte.infrastructure.persistence.entity.routes.Ruta
import com.transporte.infrastructure.persistence.entity.routes.CoordenadaRuta
import com.transporte.domain.ports.output.CoordenadaRutaRepository
import com.transporte.domain.ports.output.RutaRepository
import org.springframework.stereotype.Service

@Service
class RutaServiceImpl(
    private val coordenadaRutaRepository: CoordenadaRutaRepository,
    private val rutaRepository: RutaRepository
) : RutaService {
    override fun obtenerCoordenadasRuta(rutaId: Long): List<CoordenadaRuta> {
        return coordenadaRutaRepository.findByRutaIdOrderByOrdenAsc(rutaId)
    }

   override fun listarTodasLasRutas(): List<Ruta> {
    return rutaRepository.findAll().toList()
}


}
