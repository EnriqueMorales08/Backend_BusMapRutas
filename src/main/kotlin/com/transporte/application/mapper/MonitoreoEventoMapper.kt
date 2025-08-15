package com.transporte.application.mapper

import com.transporte.application.dto.MonitoreoEventoDTO
import com.transporte.application.util.Normalized
import com.transporte.infrastructure.persistence.entity.monitoring.MonitoreoEventoEntity

fun MonitoreoEventoDTO.toEntity(): MonitoreoEventoEntity =
    MonitoreoEventoEntity(
        evento      = evento,
        resultado   = resultado,
        mensaje     = mensaje,
        actividad   = actividad,
        componente  = componente,
        referencia  = referencia,
        detalles    = detalles,
        usuarioId   = usuarioId,
        sesionId    = sesionId,
        appVersion  = appVersion,
        deviceModel = deviceModel,
        osVersion   = osVersion,
        lat         = lat,
        lng         = lng,
        accuracyM   = accuracyM
    )

fun Normalized.toEntity(): MonitoreoEventoEntity =
    MonitoreoEventoEntity(
        evento      = dto.evento,
        resultado   = dto.resultado,
        mensaje     = dto.mensaje,
        actividad   = dto.actividad,
        componente  = dto.componente,
        referencia  = dto.referencia,
        detalles    = dto.detalles,
        usuarioId   = dto.usuarioId,
        sesionId    = dto.sesionId,
        appVersion  = dto.appVersion,
        deviceModel = dto.deviceModel,
        osVersion   = dto.osVersion,
        lat         = dto.lat,
        lng         = dto.lng,
        accuracyM   = dto.accuracyM,
        ingestaEstado = ingestaEstado,   
        ingestaError  = ingestaError,    
        rawPayload    = rawPayload       
    )
