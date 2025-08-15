package com.transporte.application.service

import com.transporte.application.dto.MonitoreoEventoDTO
import com.transporte.application.mapper.toEntity
import com.transporte.infrastructure.repository.MonitoreoEventoRepository
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class MonitoreoService(
    private val repo: MonitoreoEventoRepository
) {
    @Transactional
    fun registrar(dto: MonitoreoEventoDTO): Long {
        val saved = repo.save(dto.toEntity())
        return saved.id ?: -1
    }

    @Transactional
    fun registrarBatch(dtos: List<MonitoreoEventoDTO>): Int {
        val entities = dtos.map { it.toEntity() }
        return repo.saveAll(entities).size
    }
}
