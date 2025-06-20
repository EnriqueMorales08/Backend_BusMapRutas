package com.transporte.infrastructure.repository

import com.transporte.domain.Paradero
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
