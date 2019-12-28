package com.example.presentation.ui.scenes.all_cards

import com.example.presentation.App
import com.example.presentation.ui.scenes.base.BaseFragment
import moxy.presenter.InjectPresenter
import moxy.presenter.ProvidePresenter

class AllCardsFragment : BaseFragment(), AllCardsView {

    @InjectPresenter
    override lateinit var presenter: AllCardsPresenter

    @ProvidePresenter
    fun provideAllCardsPresenter() = App.appComponent.provideAllCardsPresenter()
}