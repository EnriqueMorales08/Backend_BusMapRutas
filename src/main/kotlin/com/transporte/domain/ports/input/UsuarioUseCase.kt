package com.transporte.domain.ports.input

import org.springframework.web.multipart.MultipartFile
import com.transporte.domain.model.Usuario

interface UsuarioUseCase {
    fun registrar(nombre: String,correo: String,celular: String,password: String,dni: String,fotoPerfil: MultipartFile,dniFrontal: MultipartFile,dniPosterior: MultipartFile): Boolean

    fun login(dni: String, password: String): Usuario?
}
