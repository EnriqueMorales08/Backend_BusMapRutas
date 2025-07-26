package com.transporte.domain.ports.output

import com.transporte.infrastructure.persistence.entity.routes.Informacion
import org.springframework.data.jpa.repository.JpaRepository

interface InformacionRepository : JpaRepository<Informacion, Long> {
    fun findByEmpresaId(empresaId: Long): Informacion?
}
