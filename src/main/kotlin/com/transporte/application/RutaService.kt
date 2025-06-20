package com.transporte.application

import com.transporte.domain.CoordenadaRuta
import com.transporte.domain.Ruta

interface RutaService {
    fun obtenerCoordenadasRuta(rutaId: Long): List<CoordenadaRuta>
    fun listarTodasLasRutas(): List<Ruta>
}
