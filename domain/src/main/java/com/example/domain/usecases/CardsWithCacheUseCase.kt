package com.example.domain.usecases

import com.example.domain.entities.PokemonCard
import io.reactivex.Single

interface CardsWithCacheUseCase {

    fun getCards(page: Int, pageSize: Int, name: String): Single<List<PokemonCard>>

    fun getCardById(id: String): Single<PokemonCard>
}