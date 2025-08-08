package com.transporte.domain.ports.output

import com.transporte.infrastructure.persistence.entity.routes.Ruta
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface RutaRepository :JpaRepository<Ruta, Long>{
    fun findByEmpresaId(id: Long): List<Ruta>
    fun deleteByEmpresaId(empresaId: Long)
}
