package com.transporte.controller

import com.transporte.application.UsuarioService
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import org.springframework.web.multipart.MultipartFile

@RestController
@RequestMapping("/api/usuarios")
class UsuarioController(
    private val usuarioService: UsuarioService
) {

    @PostMapping("/registrar", consumes = ["multipart/form-data"])
    fun registrarUsuario(
        @RequestPart("nombre") nombre: String,
        @RequestPart("correo") correo: String,
        @RequestPart("celular") celular: String,
        @RequestPart("password") password: String,
        @RequestPart("dni") dni: String,
        @RequestPart("fotoPerfil") fotoPerfil: MultipartFile,
        @RequestPart("dniFrontal") dniFrontal: MultipartFile,
        @RequestPart("dniPosterior") dniPosterior: MultipartFile
    ): ResponseEntity<String> {
        usuarioService.registrarUsuario(
            nombre,
            correo,
            celular,
            password,
            dni,
            fotoPerfil,
            dniFrontal,
            dniPosterior
        )
        return ResponseEntity.ok("Usuario registrado correctamente")
    }
}
