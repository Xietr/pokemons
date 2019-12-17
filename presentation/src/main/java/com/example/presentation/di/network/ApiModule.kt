package com.example.presentation.di.network

import com.example.data.retrofit.Api
import com.example.presentation.di.network.retrofit.RetrofitModule
import dagger.Module
import dagger.Provides
import retrofit2.Retrofit
import javax.inject.Singleton

@Module(includes = [RetrofitModule::class])
class ApiModule {

    @Provides
    @Singleton
    fun provideApi(retrofit: Retrofit): Api {
        return retrofit.create(Api::class.java)
    }

}