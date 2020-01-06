package com.example.domain.gateways

import com.example.domain.entities.PokemonCard
import io.reactivex.Completable
import io.reactivex.Single

interface CacheGateway {

    fun saveCards(cards: List<PokemonCard?>?): Completable

    fun saveCard(pokemonCard: PokemonCard): Completable

    fun getCards(page: Int, pageSize: Int, name: String): Single<List<PokemonCard?>?>

    fun getCardById(id: String): Single<PokemonCard>

    fun getFavoriteCards(page: Int, pageSize: Int): Single<List<PokemonCard?>?>

    fun updateCard(id: String, isFavourite: Int): Completable
}