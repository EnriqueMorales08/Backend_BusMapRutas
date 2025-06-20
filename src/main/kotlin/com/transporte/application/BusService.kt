package com.transporte.application

import com.transporte.domain.Bus

interface BusService {
    fun obtenerBusesPorRuta(rutaId: Long): List<Bus>
}
