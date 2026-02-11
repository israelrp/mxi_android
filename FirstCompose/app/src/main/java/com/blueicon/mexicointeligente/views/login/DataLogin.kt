package com.blueicon.mexicointeligente.views.login

data class DataLogin(

    val email: String = "",
    val password: String = "",
    val msgError: String = "",
    val showDialog: Boolean = false
)

data class LoginRequest(
    val Usuario: String,
    val Password: String
)