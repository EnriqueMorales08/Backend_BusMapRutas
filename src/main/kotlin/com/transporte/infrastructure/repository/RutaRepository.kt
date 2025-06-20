package com.transporte.infrastructure.repository

import com.transporte.domain.Ruta
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface RutaRepository :JpaRepository<Ruta, Long>
