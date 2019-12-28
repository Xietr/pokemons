package com.example.presentation.ui.scenes.all_cards

import com.example.domain.entities.PokemonCard
import com.example.domain.usecases.CardsWithCacheUseCase
import com.example.presentation.ui.scenes.base.BasePresenter
import io.reactivex.Single
import moxy.InjectViewState
import javax.inject.Inject

@InjectViewState
class AllCardsPresenter @Inject constructor(
    private val cardsWithCacheUseCase: CardsWithCacheUseCase) : BasePresenter<AllCardsView>() {

    override fun getItems(page: Int, pageSize: Int, name: String): Single<List<PokemonCard?>?> {
        return cardsWithCacheUseCase.getCards(page, pageSize, nameForSearch)
    }
}