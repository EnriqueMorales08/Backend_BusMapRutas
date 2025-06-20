package com.transporte.persistence

import org.springframework.data.jpa.repository.JpaRepository

interface UsuarioJpaRepository : JpaRepository<UsuarioEntity, String> {
    fun findByDniAndPassword(dni: String, password: String): UsuarioEntity?
    fun existsByDni(dni: String): Boolean
}
