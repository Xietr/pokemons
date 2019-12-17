package com.example.domain.gateways

import com.example.domain.entities.LoginEntity
import io.reactivex.Single

interface AuthGateway {

    fun getToken(username: String, password: String): Single<LoginEntity>

    fun getTokenByRefreshToken(refreshToken: String): Single<LoginEntity>
}