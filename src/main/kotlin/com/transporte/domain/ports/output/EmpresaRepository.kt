package com.transporte.domain.ports.output

import com.transporte.infrastructure.persistence.entity.routes.Empresa
import org.springframework.data.jpa.repository.JpaRepository

interface EmpresaRepository : JpaRepository<Empresa, Long>