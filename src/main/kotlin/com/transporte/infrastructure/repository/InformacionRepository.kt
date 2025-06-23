package com.transporte.infrastructure.repository

import com.transporte.domain.Informacion
import org.springframework.data.jpa.repository.JpaRepository

interface InformacionRepository : JpaRepository<Informacion, Long> {
    fun findByEmpresaId(empresaId: Long): Informacion?
}
