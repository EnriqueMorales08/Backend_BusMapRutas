package com.transporte.application

import com.transporte.domain.Paradero

interface ParaderoService {
   fun obtenerParaderoMasCercano(lat: Double, lng: Double, rutaId: Long): Paradero

}

