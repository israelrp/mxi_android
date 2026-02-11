package com.blueicon.mexicointeligente.views.login

import com.blueicon.mexicointeligente.api.Api
import com.blueicon.mexicointeligente.api.models.LoginResponse
import javax.inject.Inject

class LoginRepository @Inject constructor(private val api: Api) {

    suspend fun setLogin(request: LoginRequest): LoginResponse? {

        return try {
            val response = api.setLogin(request)
            if (response.isSuccessful) {
                response.body()
            } else {
                null // Handle error cases
            }
        } catch (e: Exception) {
            e.printStackTrace()
            null
        }
    }

}