package com.transporte.infrastructure.repository

import com.transporte.domain.Bus
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface BusRepository : JpaRepository<Bus, Long> {
    fun findByRutaId(rutaId: Long): List<Bus>
}
