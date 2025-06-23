package com.transporte.infrastructure.controller

import com.transporte.domain.Empresa
import com.transporte.domain.Ruta
import com.transporte.infrastructure.repository.EmpresaRepository
import com.transporte.infrastructure.dto.EmpresaDto
import com.transporte.infrastructure.dto.RutaDto

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

