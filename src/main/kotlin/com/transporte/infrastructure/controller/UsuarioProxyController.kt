package com.transporte.infrastructure.controller

import com.transporte.config.AdminApiClient
import com.transporte.application.dto.Usuario
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/api/usuarios")
class UsuarioProxyController(
    private val adminApiClient: AdminApiClient
) {

    @GetMapping("/remotos")
    fun obtenerUsuariosRemotos(@RequestParam(required = false) estado: Boolean?): ResponseEntity<List<Usuario>> {
        val usuarios = adminApiClient.obtenerUsuarios(estado)
        return ResponseEntity.ok(usuarios)
    }

    @PostMapping("/remotos")
    fun crearUsuarioRemoto(@RequestBody usuario: Usuario): ResponseEntity<String> {
        val resultado = adminApiClient.crearUsuarioEnAdmin(usuario)
        return ResponseEntity.ok(resultado)
    }
}
