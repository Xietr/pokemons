package com.example.data.auth.gateways

import com.example.data.retrofit.Api
import com.example.domain.entities.LoginEntity
import com.example.domain.gateways.AuthGateway
import io.reactivex.Single

class AuthGatewayImpl(private val api: Api) : AuthGateway {

    override fun getToken(username: String, password: String): Single<LoginEntity> {
        return api.getToken(username = username, password = password)
    }

    override fun getTokenByRefreshToken(refreshToken: String): Single<LoginEntity> {
        return api.getTokenByRefresh(refreshToken = refreshToken)
    }
}