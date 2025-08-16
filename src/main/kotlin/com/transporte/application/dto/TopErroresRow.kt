package com.transporte.application.dto

data class TopErroresRow(
    val actividad: String?,
    val componente: String?,
    val total: Long,
    val erroresApp: Long,
    val invalidosIngesta: Long,
    val pctError: Double,
    val pctInvalid: Double
)
