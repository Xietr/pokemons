package com.example.data.retrofit.gateways_imp

import com.example.data.retrofit.PokemonApi
import com.example.domain.entities.PokemonCard
import com.example.domain.gateways.NetworkGateway
import io.reactivex.Single

class NetworkGatewayImp(private val pokemonApi: PokemonApi): NetworkGateway {

    override fun getCards(page: Int, limit: Int, name: String): Single<List<PokemonCard?>?> {
        return pokemonApi.getPokemonCards(page, limit, name).flatMap{Single.just(it.cards)}
    }

    override fun getCardById(id: String): Single<List<PokemonCard?>?> {
        return pokemonApi.getCard(id).flatMap { Single.just(it.cards) }
    }
}