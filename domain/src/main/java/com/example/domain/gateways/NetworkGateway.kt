package com.example.domain.gateways

import com.example.domain.entities.PokemonCard
import io.reactivex.Single


interface NetworkGateway {

    fun getCards(page: Int, limit: Int, name: String = ""): Single<List<PokemonCard>>

    fun getCardById(id: String): Single<List<PokemonCard>>
}