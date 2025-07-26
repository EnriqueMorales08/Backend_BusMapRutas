package com.transporte.application.service

import com.transporte.infrastructure.persistence.entity.routes.CoordenadaRuta
import com.transporte.infrastructure.persistence.entity.routes.Ruta

interface RutaService {
    fun obtenerCoordenadasRuta(rutaId: Long): List<CoordenadaRuta>
    fun listarTodasLasRutas(): List<Ruta>
}
