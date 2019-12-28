package com.example.presentation.ui.scenes.detailed_card

import com.example.domain.entities.PokemonCard
import moxy.MvpView
import moxy.viewstate.strategy.AddToEndStrategy
import moxy.viewstate.strategy.StateStrategyType

interface DetailedCardView : MvpView {

    @StateStrategyType(AddToEndStrategy::class)
    fun showAllInfo(pokemonCard: PokemonCard)

    @StateStrategyType(AddToEndStrategy::class)
    fun showError(text: String)
}