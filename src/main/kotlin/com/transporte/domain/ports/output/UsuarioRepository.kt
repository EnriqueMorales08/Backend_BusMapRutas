package com.transporte.domain.ports.output

import com.transporte.domain.model.Usuario

interface UsuarioRepository {
    fun save(usuario: Usuario): Usuario
    fun findByDniAndPassword(dni: String, password: String): Usuario?
    fun existsByDni(dni: String): Boolean
}
