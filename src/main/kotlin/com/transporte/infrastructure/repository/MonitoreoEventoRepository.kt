package com.transporte.infrastructure.repository

import com.transporte.infrastructure.persistence.entity.monitoring.MonitoreoEventoEntity
import org.springframework.data.jpa.repository.JpaRepository

interface MonitoreoEventoRepository : JpaRepository<MonitoreoEventoEntity, Long>