package com.example.presentation.ui.scenes.all_cards

import android.widget.Toast
import androidx.navigation.fragment.findNavController
import com.example.presentation.App
import com.example.presentation.ui.adapters.CardsAdapter
import com.example.presentation.ui.scenes.base.base_pagination_with_search.BasePaginationSearchFragment
import moxy.presenter.InjectPresenter
import moxy.presenter.ProvidePresenter

class AllCardsFragment : BasePaginationSearchFragment(), AllCardsView {

    @InjectPresenter
    override lateinit var presenter: AllCardsPresenter

    @ProvidePresenter
    fun provideAllCardsPresenter() = App.appComponent.provideAllCardsPresenter()

    override val title: String = "Pokemons"

    override val onSearch: (String?) -> Unit = {
        val action = AllCardsFragmentDirections.actionNavigationCardsToSearchFragment(it)
        findNavController().navigate(action)
    }

    override val adapter =
        CardsAdapter(
            { Toast.makeText(context, it, Toast.LENGTH_SHORT).show() },
            {
                val action = AllCardsFragmentDirections.actionAllCardsToFragmentDetailedCard(it)
                findNavController().navigate(action)
            })
}