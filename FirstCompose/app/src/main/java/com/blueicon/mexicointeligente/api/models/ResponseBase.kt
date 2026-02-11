package com.blueicon.mexicointeligente.api.models

data class ResponseBase(
    val Status: Boolean,
    val Message: String
)

data class LoginResponse(
    val Status: Boolean,
    val Message: String,
    val userName: String,
    val userId: Int,
    val tipoUsuarioId: Int
)
