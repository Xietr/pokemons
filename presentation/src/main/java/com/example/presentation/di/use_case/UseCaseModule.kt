package com.example.presentation.di.use_case

import com.example.data.use_case_impl.CardsWithCacheUseCaseImp
import com.example.domain.gateways.CacheGateway
import com.example.domain.gateways.NetworkGateway
import com.example.domain.usecases.CardsWithCacheUseCase
import com.example.presentation.di.gateways.GatewayModule
import dagger.Module
import dagger.Provides
import javax.inject.Singleton


@Module(includes = [GatewayModule::class])
class UseCaseModule {

    @Singleton
    @Provides
    fun provideCardsWithCacheUseCase(
        cacheGateway: CacheGateway,
        networkGateway: NetworkGateway
    ): CardsWithCacheUseCase {
        return CardsWithCacheUseCaseImp(cacheGateway, networkGateway)
    }
}