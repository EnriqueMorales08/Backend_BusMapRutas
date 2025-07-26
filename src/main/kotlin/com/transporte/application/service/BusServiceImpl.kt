package com.transporte.application.service

import com.transporte.application.service.BusService
import com.transporte.infrastructure.persistence.entity.routes.Bus
import com.transporte.domain.ports.output.BusRepository
import org.springframework.stereotype.Service

@Service
class BusServiceImpl(
    private val busRepository: BusRepository
) : BusService {

    override fun obtenerBusesPorRuta(rutaId: Long): List<Bus> {
        return busRepository.findByRutaId(rutaId)
    }
}
