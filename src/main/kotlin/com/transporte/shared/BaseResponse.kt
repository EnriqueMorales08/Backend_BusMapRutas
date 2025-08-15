package com.transporte.shared

data class BaseResponse<T>(
    val message: String,
    val status: String,
    val data: T? = null
) {
    companion object {
        fun <T> ok(message: String, data: T? = null) = BaseResponse(message, "OK", data)
        fun <T> error(message: String, data: T? = null) = BaseResponse(message, "ERROR", data)
    }
}
