package com.transporte.application.service

import com.transporte.infrastructure.persistence.entity.routes.Paradero

interface ParaderoService {
   fun obtenerParaderoMasCercano(lat: Double, lng: Double, rutaId: Long): Paradero

}

