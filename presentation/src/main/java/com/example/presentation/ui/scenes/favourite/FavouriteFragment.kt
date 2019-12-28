package com.example.presentation.ui.scenes.favourite

import com.example.presentation.App
import com.example.presentation.ui.scenes.base.BaseFragment
import moxy.presenter.InjectPresenter
import moxy.presenter.ProvidePresenter

class FavouriteFragment : BaseFragment(), FavouriteView {

    @InjectPresenter
    override lateinit var presenter: FavouritePresenter

    @ProvidePresenter
    fun provideFavoritePresenter() = App.appComponent.provideFavoritePresenter()
}