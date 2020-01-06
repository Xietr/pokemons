package com.example.presentation.ui.scenes.base.base_pagination

import com.example.domain.entities.PokemonCard
import moxy.MvpView
import moxy.viewstate.strategy.AddToEndSingleStrategy
import moxy.viewstate.strategy.SkipStrategy
import moxy.viewstate.strategy.StateStrategyType

interface BasePaginationView : MvpView {

    @StateStrategyType(SkipStrategy::class)
    fun showToast(message: String)

    @StateStrategyType(SkipStrategy::class)
    fun clearAdapter()

    @StateStrategyType(AddToEndSingleStrategy::class)
    fun setRecyclerViewItems(list: List<PokemonCard>)

    @StateStrategyType(SkipStrategy::class)
    fun setIsRefreshing(isRefreshing: Boolean)

    @StateStrategyType(SkipStrategy::class)
    fun setIsProgressBarVisible(isVisible: Boolean)
}