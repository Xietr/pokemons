package com.example.presentation.di.gateways

import com.example.data.caching.room.daos.PokemonCardDao
import com.example.data.caching.room.gateways_imp.CacheGatewayImp
import com.example.data.retrofit.PokemonApi
import com.example.data.retrofit.gateways_imp.NetworkGatewayImp
import com.example.domain.gateways.CacheGateway
import com.example.domain.gateways.NetworkGateway
import com.example.presentation.di.network.ApiModule
import com.example.presentation.di.room.RoomModule
import dagger.Module
import dagger.Provides
import javax.inject.Singleton


@Module(includes = [ApiModule::class, RoomModule::class])
class GatewayModule {

    @Singleton
    @Provides
    fun provideNetworkGateway(pokemonApi: PokemonApi): NetworkGateway {
        return NetworkGatewayImp(pokemonApi)
    }

    @Singleton
    @Provides
    fun provideCacheGateway(dao: PokemonCardDao): CacheGateway =
        CacheGatewayImp(dao)
}