package com.transporte.domain.ports.output

import com.transporte.infrastructure.persistence.entity.routes.Bus
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface BusRepository : JpaRepository<Bus, Long> {
    fun findByRutaId(rutaId: Long): List<Bus>
}
