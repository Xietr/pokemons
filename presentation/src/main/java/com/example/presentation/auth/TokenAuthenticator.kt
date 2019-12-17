package com.example.presentation.auth

import android.content.Context
import com.example.data.retrofit.Api
import com.example.presentation.App
import com.example.presentation.ui.scenes.authorization.AuthorizationActivity
import okhttp3.Authenticator
import okhttp3.Request
import okhttp3.Response
import okhttp3.Route
import java.io.IOException
import javax.inject.Inject

class TokenAuthenticator : Authenticator {

    @Inject
    lateinit var authGateway: Api

    private var hasInject = false

    private val sharedPreferences = AuthorizationActivity.ContextOfApplication.getSharedPreferences(
        AuthorizationActivity.TOKENS,
        Context.MODE_PRIVATE
    )

    override fun authenticate(route: Route?, response: Response): Request? {
        synchronized(this) {
            val responseBody = response.body.toString()
            val refreshToken =
                sharedPreferences.getString(AuthorizationActivity.REFRESH_TOKEN, "")

            if (!hasInject) {
                App.appComponent.inject(this)
                hasInject = true
            }

            val token = when {
                responseBody.contains("token provided has expired") -> null
                refreshToken == "" -> null
                else -> try {
                    authGateway.getTokenByRefresh(refreshToken = refreshToken!!).blockingGet()
                        .let {
                            val editor = sharedPreferences.edit()
                            editor.putString(AuthorizationActivity.ACCESS_TOKEN, it.access_token)
                            editor.putString(AuthorizationActivity.REFRESH_TOKEN, it.refresh_token)
                            editor.apply()

                            it.access_token
                        }
                } catch (e: Throwable) {
                    e.printStackTrace()
                    null
                }
            }

            return if (token == null) {
                logout()
                throw IOException(responseBody)
            } else {
                response.request
                    .newBuilder()
                    .header("Authorization", "Bearer $token")
                    .build()
            }
        }
    }

    private fun logout() {
        sharedPreferences.edit().clear().apply()
    }
}