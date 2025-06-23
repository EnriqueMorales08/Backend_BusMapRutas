package com.transporte.application

import com.transporte.infrastructure.dto.InformacionDto
import com.transporte.infrastructure.repository.InformacionRepository
import org.springframework.stereotype.Service

@Service
class InformacionService(
    private val informacionRepository: InformacionRepository
) {

    fun obtenerInformacionPorEmpresa(empresaId: Long): InformacionDto? {
        val info = informacionRepository.findByEmpresaId(empresaId)
        return info?.let {
            InformacionDto(
                numeroUnidades = it.numeroUnidades,
                duracionRecorrido = it.duracionRecorrido,
                longitudRecorrido = it.longitudRecorrido,
                inicioServicioLunesViernes = it.inicioServicioLunesViernes,
                inicioServicioSabado = it.inicioServicioSabado,
                inicioServicioDomingo = it.inicioServicioDomingo,
                finServicioLunesViernes = it.finServicioLunesViernes,
                finServicioSabado = it.finServicioSabado,
                finServicioDomingo = it.finServicioDomingo,
                mensaje = it.mensaje
            )
        }
    }
}
