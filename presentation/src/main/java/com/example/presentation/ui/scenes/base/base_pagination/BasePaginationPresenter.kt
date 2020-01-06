package com.example.presentation.ui.scenes.base.base_pagination

import com.example.domain.entities.PokemonCard
import io.reactivex.Single
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import moxy.MvpPresenter

abstract class BasePaginationPresenter<T : BasePaginationView> : MvpPresenter<T>() {

    internal val compositeDisposable = CompositeDisposable()

    internal var currentPage = 1
    internal var isLoading = false

    internal var isLastPage = false

    internal var nameForSearch: String = ""

    abstract fun getItems(page: Int, pageSize: Int, name: String = ""): Single<List<PokemonCard?>?>


    override fun onFirstViewAttach() {
        super.onFirstViewAttach()
        getCards()
    }

    fun getCards() {
        if (isLoading || isLastPage) {
            return
        }

        getItems(currentPage, PAGE_SIZE, nameForSearch)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .doOnSubscribe {
                isLoading = true
                viewState.setIsProgressBarVisible(true)
            }
            .doOnSuccess {
                currentPage++
            }
            .doFinally {
                viewState.setIsProgressBarVisible(false)
                isLoading = false
                viewState.setIsRefreshing(false)
            }
            .subscribe({
                if (it.isNullOrEmpty()) {
                    isLastPage = true
                    viewState.showToast("Карточек нет")
                } else {
                    viewState.updateRecyclerView(it)
                }
            }, {
                it.printStackTrace()
                viewState.showToast("Ошибка покдлючения")
            })
            .let(compositeDisposable::add)
    }

    fun onRefresh() {
        viewState.setIsRefreshing(true)
        currentPage = 1
        viewState.clearAdapter()
        getCards()
    }

    override fun onDestroy() {
        super.onDestroy()
        compositeDisposable.clear()
    }


    companion object {
        const val PAGE_SIZE = 10
    }
}