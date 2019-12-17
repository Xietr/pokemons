package com.example.data.retrofit

import com.example.data.BuildConfig.CLIENT_ID
import com.example.data.BuildConfig.CLIENT_SECRET
import com.example.domain.entities.LoginEntity
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Query

interface Api {

    @GET("oauth/v2/token")
    fun getToken(
        @Query("client_id") clientId: String = CLIENT_ID,
        @Query("client_secret") clientSecret: String = CLIENT_SECRET,
        @Query("grant_type") grant_type: String = GRANT_TYPE_PASSWORD,
        @Query("username") username: String,
        @Query("password") password: String
    ): Single<LoginEntity>

    @GET("oauth/v2/token")
    @Headers("Accept: application/json, */*")
    fun getTokenByRefresh(
        @Query("client_id") clientId: String = CLIENT_ID,
        @Query("client_secret") clientSecret: String = CLIENT_SECRET,
        @Query("grant_type") grant_type: String = GRANT_TYPE_REFRESH_TOKEN,
        @Query("refresh_token") refreshToken: String
    ): Single<LoginEntity>

    companion object {
        const val GRANT_TYPE_PASSWORD = ""
        const val GRANT_TYPE_REFRESH_TOKEN = ""
    }
}