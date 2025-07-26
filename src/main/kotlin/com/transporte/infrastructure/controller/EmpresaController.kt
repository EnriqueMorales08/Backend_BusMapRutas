package com.transporte.infrastructure.controller

import com.transporte.infrastructure.persistence.entity.routes.Empresa
import com.transporte.infrastructure.persistence.entity.routes.Ruta
import com.transporte.domain.ports.output.EmpresaRepository
import com.transporte.application.dto.EmpresaDto
import com.transporte.application.dto.RutaDto

import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/api/empresas")
class EmpresaController(
    private val empresaRepository: EmpresaRepository
) {

   @GetMapping
fun listarEmpresas(): List<EmpresaDto> {
    return empresaRepository.findAll().map { EmpresaDto(it.id, it.nombre) }
}

@GetMapping("/{id}/rutas")
fun listarRutasPorEmpresa(@PathVariable id: Long): List<RutaDto> {
    val empresa = empresaRepository.findById(id).orElseThrow()
    return empresa.rutas.map { RutaDto(it.id, it.nombre) }
}

}

