package com.transporte.application

import com.transporte.domain.Usuario
import com.transporte.domain.ports.output.UsuarioRepository
import org.springframework.beans.factory.annotation.Value
import org.springframework.stereotype.Service
import org.springframework.web.multipart.MultipartFile
import java.nio.file.Files
import java.nio.file.Paths
import java.util.*

@Service
class UsuarioService(
    private val usuarioRepository: UsuarioRepository
) {

    @Value("\${upload.folder}")
    lateinit var uploadFolder: String

    fun registrarUsuario(
        nombre: String,
        correo: String,
        celular: String,
        password: String,
        dni: String,
        fotoPerfil: MultipartFile,
        dniFrontal: MultipartFile,
        dniPosterior: MultipartFile
    ): Usuario {
        // Crear carpeta si no existe
        val uploadPath = Paths.get(uploadFolder)
        if (!Files.exists(uploadPath)) {
            Files.createDirectories(uploadPath)
        }

        // Guardar las 3 imágenes con nombres únicos
        val perfilFileName = "${UUID.randomUUID()}_perfil.jpg"
        val frontalFileName = "${UUID.randomUUID()}_dni_frontal.jpg"
        val posteriorFileName = "${UUID.randomUUID()}_dni_posterior.jpg"

        fotoPerfil.transferTo(uploadPath.resolve(perfilFileName).toFile())
        dniFrontal.transferTo(uploadPath.resolve(frontalFileName).toFile())
        dniPosterior.transferTo(uploadPath.resolve(posteriorFileName).toFile())

        // Crear el objeto usuario con las rutas relativas
        val usuario = Usuario(
            nombre = nombre,
            correo = correo,
            celular = celular,
            password = password,
            dni = dni,
            fotoPerfil = "/uploads/$perfilFileName",
            dniFrontal = "/uploads/$frontalFileName",
            dniPosterior = "/uploads/$posteriorFileName",
            estado=false
        )

        return usuarioRepository.save(usuario)
    }
}
