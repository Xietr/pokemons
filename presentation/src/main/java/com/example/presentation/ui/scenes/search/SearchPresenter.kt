package com.example.presentation.ui.scenes.search

import com.example.domain.entities.PokemonCard
import com.example.domain.usecases.CardsWithCacheUseCase
import com.example.presentation.ui.scenes.base.base_pagination.BasePaginationPresenter
import io.reactivex.Single
import moxy.InjectViewState
import javax.inject.Inject

@InjectViewState
class SearchPresenter @Inject constructor(private val cardsWithCacheUseCase: CardsWithCacheUseCase) :
    BasePaginationPresenter<SearchView>() {

    override fun getItems(page: Int, pageSize: Int, name: String): Single<List<PokemonCard>> {
        return cardsWithCacheUseCase.getCards(page, pageSize, name)
    }
}