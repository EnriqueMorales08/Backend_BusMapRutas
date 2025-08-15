package com.transporte.application.service

import com.transporte.application.dto.MonitoreoEventoDTO
import com.transporte.application.mapper.toEntity
import com.transporte.application.util.MonitoreoNormalizer
import com.transporte.infrastructure.persistence.entity.monitoring.MonitoreoEventoEntity
import com.transporte.infrastructure.repository.MonitoreoEventoRepository
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Propagation
import org.springframework.transaction.annotation.Transactional

@Service
class MonitoreoTxService(
    private val repo: MonitoreoEventoRepository
) {
    @Transactional(propagation = Propagation.REQUIRES_NEW)
    fun saveNormalized(dto: MonitoreoEventoDTO): Long {
        val n = MonitoreoNormalizer.normalize(dto)
        val entity: MonitoreoEventoEntity = n.toEntity()
        return repo.save(entity).id!!
    }
}

@Service
class MonitoreoServiceParcialSiempreInserta(
    private val tx: MonitoreoTxService
) {
    fun registrarBatchSiempreInserta(dtos: List<MonitoreoEventoDTO>): Pair<Int, List<String>> {
        var ok = 0
        val errores = mutableListOf<String>()
        dtos.forEachIndexed { idx, dto ->
            try {
                tx.saveNormalized(dto)
                ok++
            } catch (ex: Exception) {
                errores += "idx=$idx: ${rootMessage(ex)}"
            }
        }
        return ok to errores
    }

    private fun rootMessage(ex: Throwable): String {
        var e = ex
        while (e.cause != null) e = e.cause!!
        return (e.message ?: ex.javaClass.simpleName).take(200)
    }
}
