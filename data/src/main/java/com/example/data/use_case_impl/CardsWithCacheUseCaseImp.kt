package com.example.data.use_case_impl

import com.example.domain.entities.PokemonCard
import com.example.domain.gateways.CacheGateway
import com.example.domain.gateways.NetworkGateway
import com.example.domain.usecases.CardsWithCacheUseCase
import io.reactivex.Single

class CardsWithCacheUseCaseImp(
    private val cacheGateway: CacheGateway,
    private val networkGateway: NetworkGateway
) : CardsWithCacheUseCase {

    override fun getCards(page: Int, pageSize: Int, name: String): Single<List<PokemonCard?>?> {
        return networkGateway.getCards(page, pageSize, name)
            .flatMap { cacheGateway.saveCards(it).andThen(Single.just(it)) }
            .onErrorResumeNext { cacheGateway.getCards(page, pageSize, name) }
    }

    override fun getCardById(id: String): Single<PokemonCard> {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }
}