package com.example.presentation.ui.scenes.favourite

import com.example.domain.entities.PokemonCard
import com.example.domain.gateways.CacheGateway
import com.example.presentation.ui.scenes.base.BasePresenter
import io.reactivex.Single
import moxy.InjectViewState
import javax.inject.Inject

@InjectViewState
class FavouritePresenter @Inject constructor(
    private val cacheGateway: CacheGateway) : BasePresenter<FavouriteView>() {

    override fun getItems(page: Int, pageSize: Int, name: String): Single<List<PokemonCard?>?> {
        return cacheGateway.getFavoriteCards(page, pageSize)
    }
}