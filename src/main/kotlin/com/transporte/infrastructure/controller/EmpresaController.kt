package com.transporte.infrastructure.controller

import com.transporte.infrastructure.persistence.entity.routes.Empresa
import com.transporte.infrastructure.persistence.entity.routes.Ruta
import com.transporte.domain.ports.output.EmpresaRepository
import com.transporte.domain.ports.output.RutaRepository
import com.transporte.domain.ports.output.InformacionRepository
import com.transporte.domain.ports.output.CoordenadaRutaRepository
import com.transporte.domain.ports.output.ParaderoRepository
import com.transporte.application.dto.EmpresaDto
import com.transporte.application.dto.RutaDto

import org.springframework.web.bind.annotation.*
import org.springframework.transaction.annotation.Transactional

@RestController
@RequestMapping("/api/empresas")
class EmpresaController(
    private val empresaRepository: EmpresaRepository,
    private val rutaRepository: RutaRepository,
    private val informacionRepository: InformacionRepository,
    private val coordenadaRutaRepository: CoordenadaRutaRepository,
    private val paraderoRepository: ParaderoRepository

) {

   @GetMapping
    fun listarEmpresas(): List<EmpresaDto> {
        return empresaRepository.findAll().map { EmpresaDto(it.id, it.nombre) }
    }

    @PostMapping
    fun crearEmpresa(@RequestBody dto: EmpresaDto): EmpresaDto {
        val nueva = empresaRepository.save(Empresa(nombre = dto.nombre))
        return EmpresaDto(nueva.id, nueva.nombre)
    }

    @PutMapping("/{id}")
    fun actualizarEmpresa(@PathVariable id: Long, @RequestBody dto: EmpresaDto): EmpresaDto {
        val empresa = empresaRepository.findById(id).orElseThrow()
        val actualizada = empresa.copy(nombre = dto.nombre)
        val guardada = empresaRepository.save(actualizada)
        return EmpresaDto(guardada.id, guardada.nombre)
    }

    @DeleteMapping("/{id}")
    @Transactional
    fun eliminarEmpresa(@PathVariable id: Long) {
        val rutas = rutaRepository.findByEmpresaId(id)
        val rutaIds = rutas.map { it.id }
        rutaIds.forEach { rutaId ->
            coordenadaRutaRepository.deleteByRutaId(rutaId)
        }
        paraderoRepository.deleteByRutaIdIn(rutaIds)
        informacionRepository.deleteByEmpresaId(id)
        rutaRepository.deleteAll(rutas)
        empresaRepository.deleteById(id)
    }


    @GetMapping("/{id}/rutas")
    fun listarRutasPorEmpresa(@PathVariable id: Long): List<RutaDto> {
        val empresa = empresaRepository.findById(id).orElseThrow()
        return empresa.rutas.map { RutaDto(it.id, it.nombre) }
    }

    @PostMapping("/{id}/rutas")
    fun crearRuta(@PathVariable id: Long, @RequestBody dto: RutaDto): RutaDto {
        val empresa = empresaRepository.findById(id).orElseThrow()
        val ruta = Ruta(
            nombre = dto.nombre,
            empresa = empresa
        )
        val rutaGuardada = rutaRepository.save(ruta)
        return RutaDto(rutaGuardada.id, rutaGuardada.nombre)
    }

    @PutMapping("/{idEmpresa}/rutas/{idRuta}")
    fun actualizarRuta(@PathVariable idEmpresa: Long, @PathVariable idRuta: Long, @RequestBody dto: RutaDto): RutaDto {
        val ruta = rutaRepository.findById(idRuta).orElseThrow()
        val actualizada = ruta.copy(nombre = dto.nombre)
        val guardada = rutaRepository.save(actualizada)
        return RutaDto(guardada.id, guardada.nombre)
    }
}
