package com.transporte.infrastructure.repository

import com.transporte.domain.Empresa
import org.springframework.data.jpa.repository.JpaRepository

interface EmpresaRepository : JpaRepository<Empresa, Long>