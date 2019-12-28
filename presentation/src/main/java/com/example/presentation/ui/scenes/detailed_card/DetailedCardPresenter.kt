package com.example.presentation.ui.scenes.detailed_card

import com.example.domain.gateways.CacheGateway
import com.example.domain.gateways.NetworkGateway
import com.example.presentation.ui.scenes.base.BasePresenter
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import moxy.InjectViewState
import moxy.MvpPresenter
import javax.inject.Inject

@InjectViewState
class DetailedCardPresenter @Inject constructor(
    private val networkGateway: NetworkGateway,
    private val cacheGateway: CacheGateway
) : MvpPresenter<DetailedCardView>() {

    private var compositeDisposable = CompositeDisposable()

    fun getCard(id: String) {
        networkGateway.getCardById(id)
            .observeOn(AndroidSchedulers.mainThread())
            .subscribeOn(Schedulers.io())
            .subscribe({
                val card = it!![0]!!
                viewState.showAllInfo(card)
            }, {
                it.printStackTrace()
                getCardByIdFromCache(id)
            }).let(compositeDisposable::add)
    }

    private fun getCardByIdFromCache(id: String) {
        cacheGateway.getCardById(id)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({
                viewState.showAllInfo(it)
            }, {
                it.printStackTrace()
                viewState.showError("Ошибка подключения")
            }).let(compositeDisposable::add)
    }

    override fun onDestroy() {
        super.onDestroy()
        compositeDisposable.clear()
    }
}