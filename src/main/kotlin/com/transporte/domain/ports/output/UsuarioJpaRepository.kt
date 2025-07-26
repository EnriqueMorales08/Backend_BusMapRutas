package com.transporte.domain.ports.output

import com.transporte.infrastructure.persistence.entity.usuario.UsuarioEntity
import org.springframework.data.jpa.repository.JpaRepository

interface UsuarioJpaRepository : JpaRepository<UsuarioEntity, String> {
    fun findByDniAndPassword(dni: String, password: String): UsuarioEntity?
    fun existsByDni(dni: String): Boolean
}
