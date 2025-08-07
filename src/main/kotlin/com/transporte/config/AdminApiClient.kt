package com.transporte.config

import com.transporte.application.dto.Usuario
import org.springframework.core.ParameterizedTypeReference
import org.springframework.http.*
import org.springframework.stereotype.Service
import org.springframework.web.client.RestTemplate
import org.springframework.web.util.UriComponentsBuilder

@Service
class AdminApiClient {

    private val restTemplate = RestTemplate()
    private val baseUrl = "http://137.184.200.155:8080/api"

    fun obtenerUsuarios(estado: Boolean?): List<Usuario> {
        val uriBuilder = UriComponentsBuilder
            .fromHttpUrl("$baseUrl/usuarios")

        estado?.let {
            uriBuilder.queryParam("estado", it)
        }

        val uri = uriBuilder.toUriString()

        return try {
            val response: ResponseEntity<List<Usuario>> = restTemplate.exchange(
                uri,
                HttpMethod.GET,
                null,
                object : ParameterizedTypeReference<List<Usuario>>() {}
            )
            response.body ?: emptyList()
        } catch (ex: Exception) {
            println("Error al obtener usuarios: ${ex.message}")
            emptyList()
        }
    }

    fun crearUsuarioEnAdmin(usuario: Usuario): String {
        val request = HttpEntity(usuario, createJsonHeaders())

        return try {
            val response = restTemplate.postForEntity(
                "$baseUrl/usuarios",
                request,
                Map::class.java
            )
            response.body?.get("mensaje")?.toString() ?: "Sin mensaje"
        } catch (ex: Exception) {
            println("Error al crear usuario: ${ex.message}")
            "Error al crear usuario"
        }
    }

    private fun createJsonHeaders(): HttpHeaders {
        return HttpHeaders().apply {
            contentType = MediaType.APPLICATION_JSON
        }
    }
}
