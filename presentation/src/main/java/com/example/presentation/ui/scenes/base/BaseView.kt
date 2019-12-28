package com.example.presentation.ui.scenes.base

import com.example.domain.entities.PokemonCard
import moxy.MvpView
import moxy.viewstate.strategy.AddToEndStrategy
import moxy.viewstate.strategy.SkipStrategy
import moxy.viewstate.strategy.StateStrategyType

interface BaseView : MvpView {

    @StateStrategyType(SkipStrategy::class)
    fun showToast(message: String)

    @StateStrategyType(SkipStrategy::class)
    fun clearAdapter()

    @StateStrategyType(AddToEndStrategy::class)
    fun updateRecyclerView(list: List<PokemonCard?>?)

    @StateStrategyType(SkipStrategy::class)
    fun setIsRefreshing(isRefreshing: Boolean)

    @StateStrategyType(SkipStrategy::class)
    fun setIsProgressBarVisible(isVisible: Boolean)
}