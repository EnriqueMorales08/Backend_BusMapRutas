package com.transporte.domain.ports.output

import com.transporte.domain.Usuario

interface UsuarioRepository {
    fun save(usuario: Usuario): Usuario
    fun findByDniAndPassword(dni: String, password: String): Usuario?
    fun existsByDni(dni: String): Boolean
}
