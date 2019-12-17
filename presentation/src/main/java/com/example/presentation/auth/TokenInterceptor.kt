package com.example.presentation.auth

import android.content.Context
import com.example.presentation.ui.scenes.authorization.AuthorizationActivity
import okhttp3.Interceptor
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.Protocol
import okhttp3.Response
import okhttp3.ResponseBody.Companion.toResponseBody

class TokenInterceptor : Interceptor {

    override fun intercept(chain: Interceptor.Chain): Response {
        val sharedPreferences = AuthorizationActivity.ContextOfApplication.getSharedPreferences(
            AuthorizationActivity.TOKENS,
            Context.MODE_PRIVATE
        )
        val token =
            sharedPreferences.getString(AuthorizationActivity.ACCESS_TOKEN, "")

        val request = chain.request()

        return try {
            if (token != "") {
                chain.proceed(
                    request
                        .newBuilder()
                        .addHeader("Authorization", "Bearer $token")
                        .addHeader("Accept", "*/*")
                        .build()
                )
            } else {
                chain.proceed(request)
            }
        } catch (e: Exception) {
            val message = e.message
            e.printStackTrace()

            Response.Builder()
                .body(
                    (message ?: "").toResponseBody("-".toMediaTypeOrNull())
                )
                .protocol(Protocol.HTTP_2)
                .code(404)
                .request(request)
                .message(message ?: "")
                .build()
        }
    }
}
