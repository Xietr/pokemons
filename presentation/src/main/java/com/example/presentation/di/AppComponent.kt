package com.example.presentation.di

import com.example.presentation.auth.TokenAuthenticator
import com.example.presentation.di.network.ApiModule
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [ApiModule::class])
interface AppComponent {

    fun inject(target: TokenAuthenticator)
}