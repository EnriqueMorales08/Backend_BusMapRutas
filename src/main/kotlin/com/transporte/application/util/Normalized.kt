package com.transporte.application.util

import com.fasterxml.jackson.module.kotlin.jacksonObjectMapper
import com.transporte.application.dto.MonitoreoEventoDTO

data class Normalized(
    val dto: MonitoreoEventoDTO,             
    val ingestaEstado: String,               
    val ingestaError: String?,               
    val rawPayload: Map<String, Any?>
)

object MonitoreoNormalizer {
    private val mapper = jacksonObjectMapper().findAndRegisterModules()

    fun normalize(dto: MonitoreoEventoDTO): Normalized {
        val raw: Map<String, Any?> = mapper.convertValue(dto, Map::class.java) as Map<String, Any?>
        val problemas = mutableListOf<String>()

        val eventoFinal = (dto.evento ?: "").trim()
        if (eventoFinal.isBlank()) problemas += "evento vacío"

        val resOrig = dto.resultado
        val resultadoFinal = if (resOrig == "OK" || resOrig == "ERROR") resOrig else {
            problemas += "resultado inválido: $resOrig"
            "ERROR"
        }

        val mensajeFinal = dto.mensaje ?: if (resultadoFinal == "OK") "OK" else "ERROR"

        val dtoNorm = dto.copy(
            evento   = if (eventoFinal.isBlank()) "INVALID" else eventoFinal,
            resultado= resultadoFinal,
            mensaje  = mensajeFinal
        )

        val estado = if (problemas.isEmpty()) "VALID" else "INVALID"
        val error  = if (problemas.isEmpty()) null else problemas.joinToString("; ")
        return Normalized(dtoNorm, estado, error, raw)
    }
}
