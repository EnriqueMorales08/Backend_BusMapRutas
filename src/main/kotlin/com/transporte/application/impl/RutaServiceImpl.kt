package com.transporte.infrastructure.service

import com.transporte.application.RutaService
import com.transporte.domain.Ruta
import com.transporte.domain.CoordenadaRuta
import com.transporte.infrastructure.repository.CoordenadaRutaRepository
import com.transporte.infrastructure.repository.RutaRepository
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
