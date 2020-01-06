package com.example.presentation.ui.scenes.detailed_card

import com.example.domain.usecases.CardsWithCacheUseCase
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import moxy.InjectViewState
import moxy.MvpPresenter
import javax.inject.Inject

@InjectViewState
class DetailedCardPresenter @Inject constructor(
    private val cardsWithCacheUseCase: CardsWithCacheUseCase
) : MvpPresenter<DetailedCardView>() {

    private var compositeDisposable = CompositeDisposable()

    fun getCard(id: String) {
        cardsWithCacheUseCase.getCardById(id)
            .observeOn(AndroidSchedulers.mainThread())
            .subscribeOn(Schedulers.io())
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