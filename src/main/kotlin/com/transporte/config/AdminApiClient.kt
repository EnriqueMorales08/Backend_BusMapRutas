package com.transporte.config

import com.transporte.application.dto.Usuario
import org.springframework.core.ParameterizedTypeReference
import org.springframework.http.HttpEntity
import org.springframework.http.HttpHeaders
import org.springframework.http.HttpMethod
import org.springframework.http.MediaType
import org.springframework.http.ResponseEntity
import org.springframework.stereotype.Service
import org.springframework.web.client.RestTemplate
import org.springframework.web.util.UriComponentsBuilder


@Service
class AdminApiClient {

    private val restTemplate = RestTemplate()
    private val backendAdminBaseUrl = "http://137.184.200.155:8080/api"
    private val adminBaseUrl = "http://137.184.200.155:8080/api/usuarios"

    fun obtenerUsuarios(estado: Boolean?): List<Usuario> {
        val uriBuilder = UriComponentsBuilder
            .fromHttpUrl("$backendAdminBaseUrl/usuarios")

        if (estado != null) {
            uriBuilder.queryParam("estado", estado)
        }

        val uri = uriBuilder.toUriString()

        val response: ResponseEntity<List<Usuario>> = restTemplate.exchange(
            uri,
            HttpMethod.GET,
            null,
            object : ParameterizedTypeReference<List<Usuario>>() {}
        )

        return response.body ?: emptyList()
    }

    fun crearUsuarioEnAdmin(usuario: Usuario): String {
        val headers = HttpHeaders()
        headers.contentType = MediaType.APPLICATION_JSON
        val request = HttpEntity(usuario, headers)

        val response = restTemplate.postForEntity(adminBaseUrl, request, Map::class.java)

        return response.body?.get("mensaje").toString()
    }
}
