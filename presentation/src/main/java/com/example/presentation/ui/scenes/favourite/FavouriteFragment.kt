package com.example.presentation.ui.scenes.favourite

import android.widget.Toast
import androidx.navigation.fragment.findNavController
import com.example.presentation.App
import com.example.presentation.ui.adapters.CardsAdapter
import com.example.presentation.ui.scenes.base.base_pagination_with_search.BasePaginationSearchFragment
import moxy.presenter.InjectPresenter
import moxy.presenter.ProvidePresenter

class FavouriteFragment : BasePaginationSearchFragment(), FavouriteView {

    @InjectPresenter
    override lateinit var presenter: FavouritePresenter

    @ProvidePresenter
    fun provideFavoritePresenter() = App.appComponent.provideFavoritePresenter()

    override val title: String = "Избранное"

    override val onSearch: (String?) -> Unit = {
        val action = FavouriteFragmentDirections.actionNavigationFavouriteToSearchFragment(it)
        findNavController().navigate(action)
    }

    override val adapter = CardsAdapter(
        { Toast.makeText(context, it, Toast.LENGTH_SHORT).show() },
        {
            val action = FavouriteFragmentDirections.actionNavigationFavouriteToFragmentDetailedCard(it)
            findNavController().navigate(action)
        })
}