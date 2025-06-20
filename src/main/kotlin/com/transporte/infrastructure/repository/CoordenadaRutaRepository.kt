package com.transporte.infrastructure.repository

import com.transporte.domain.CoordenadaRuta
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository


interface CoordenadaRutaRepository : JpaRepository<CoordenadaRuta, Long> {
    fun findByRutaIdOrderByOrdenAsc(rutaId: Long): List<CoordenadaRuta>
}
