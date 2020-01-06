package com.example.presentation.di

import com.example.presentation.di.gateways.GatewayModule
import com.example.presentation.di.use_case.UseCaseModule
import com.example.presentation.ui.adapters.CardsAdapter
import com.example.presentation.ui.scenes.all_cards.AllCardsPresenter
import com.example.presentation.ui.scenes.detailed_card.DetailedCardPresenter
import com.example.presentation.ui.scenes.favourite.FavouritePresenter
import com.example.presentation.ui.scenes.search.SearchPresenter
import dagger.Component
import javax.inject.Singleton


@Singleton
@Component(modules = [GatewayModule::class, UseCaseModule::class])
interface AppComponent {

    fun provideAllCardsPresenter(): AllCardsPresenter
    fun provideDetailedCardPresenter(): DetailedCardPresenter
    fun provideFavoritePresenter(): FavouritePresenter
    fun provideSearchPresenter(): SearchPresenter

    fun inject(target: CardsAdapter)
//    fun provideSearchPresenter(): SearchPresenter
//    fun provideFavouriteCardsPresenter():
}