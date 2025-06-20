package com.transporte.application.impl

import com.transporte.application.BusService
import com.transporte.domain.Bus
import com.transporte.infrastructure.repository.BusRepository
import org.springframework.stereotype.Service

@Service
class BusServiceImpl(
    private val busRepository: BusRepository
) : BusService {

    override fun obtenerBusesPorRuta(rutaId: Long): List<Bus> {
        return busRepository.findByRutaId(rutaId)
    }
}
