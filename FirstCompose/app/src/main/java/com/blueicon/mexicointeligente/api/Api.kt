package com.blueicon.mexicointeligente.api

import com.blueicon.mexicointeligente.api.models.LoginResponse
import com.blueicon.mexicointeligente.views.login.LoginRequest
import com.blueicon.mexicointeligente.utils.Constants
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.POST

interface Api {

    @POST(Constants.LOGIN_END_POINT)
    suspend fun setLogin(@Body params: LoginRequest): Response<LoginResponse>

}