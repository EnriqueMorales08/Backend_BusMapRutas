package com.transporte.infrastructure.repository

import com.transporte.domain.model.Usuario
import com.transporte.domain.ports.output.UsuarioRepository
import com.transporte.domain.ports.output.UsuarioJpaRepository
import com.transporte.infrastructure.persistence.entity.usuario.UsuarioEntity
import org.springframework.stereotype.Repository

@Repository
class UsuarioRepositoryImpl(
    private val jpa: UsuarioJpaRepository
) : UsuarioRepository {

    override fun save(usuario: Usuario): Usuario {
        val entity = UsuarioEntity(
            nombre = usuario.nombre,
            correo = usuario.correo,
            celular = usuario.celular,
            password = usuario.password,
            dni = usuario.dni,
            fotoPerfil = usuario.fotoPerfil,
            dniFrontal = usuario.dniFrontal,
            dniPosterior = usuario.dniPosterior,
            estado = usuario.estado
        )
        val savedEntity = jpa.save(entity)

        return Usuario(
            nombre = savedEntity.nombre,
            correo = savedEntity.correo,
            celular = savedEntity.celular,
            password = savedEntity.password,
            dni = savedEntity.dni,
            fotoPerfil = savedEntity.fotoPerfil,
            dniFrontal = savedEntity.dniFrontal,
            dniPosterior = savedEntity.dniPosterior,
            estado = savedEntity.estado
        )
    }

    override fun findByDniAndPassword(dni: String, password: String): Usuario? {
        val entity = jpa.findByDniAndPassword(dni, password) ?: return null
        return Usuario(
            nombre = entity.nombre,
            correo = entity.correo,
            celular = entity.celular,
            password = entity.password,
            dni = entity.dni,
            fotoPerfil = entity.fotoPerfil,
            dniFrontal = entity.dniFrontal,
            dniPosterior = entity.dniPosterior,
            estado = entity.estado
        )
    }

    override fun existsByDni(dni: String): Boolean = jpa.existsByDni(dni)
}
