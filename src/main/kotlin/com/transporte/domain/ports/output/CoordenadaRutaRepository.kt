package com.transporte.domain.ports.output

import com.transporte.infrastructure.persistence.entity.routes.CoordenadaRuta
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository


interface CoordenadaRutaRepository : JpaRepository<CoordenadaRuta, Long> {
    fun findByRutaIdOrderByOrdenAsc(rutaId: Long): List<CoordenadaRuta>
}
