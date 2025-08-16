package com.transporte.infrastructure.repository

import com.transporte.infrastructure.persistence.entity.monitoring.MonitoreoEventoEntity
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.JpaSpecificationExecutor

interface MonitoreoEventoRepository : JpaRepository<MonitoreoEventoEntity, Long>, JpaSpecificationExecutor<MonitoreoEventoEntity>