package com.transporte.application.service

import com.transporte.infrastructure.persistence.entity.routes.Bus

interface BusService {
    fun obtenerBusesPorRuta(rutaId: Long): List<Bus>
}
