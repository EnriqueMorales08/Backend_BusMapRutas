package com.transporte.domain.ports.output

import com.transporte.infrastructure.persistence.entity.routes.Paradero
import org.springframework.data.jpa.repository.Query
import org.springframework.data.repository.query.Param
import org.springframework.data.jpa.repository.JpaRepository

interface ParaderoRepository : JpaRepository <Paradero, Long> {

   @Query("""
        SELECT p FROM Paradero p 
        WHERE p.ruta.id = :rutaId 
        ORDER BY 
            SQRT(POWER(p.latitud - :lat, 2) + POWER(p.longitud - :lng, 2))
    """)
    fun findParaderosOrdenadosPorDistancia(
        @Param("lat") lat: Double,
        @Param("lng") lng: Double,
        @Param("rutaId") rutaId: Long
    ): List<Paradero>

}
