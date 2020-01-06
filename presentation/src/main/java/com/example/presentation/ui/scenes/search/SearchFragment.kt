package com.example.presentation.ui.scenes.search

import android.app.ActivityManager
import android.os.Bundle
import android.view.Menu
import android.view.MenuInflater
import android.view.View
import android.widget.Toast
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.example.presentation.App
import com.example.presentation.R
import com.example.presentation.ui.adapters.CardsAdapter
import com.example.presentation.ui.scenes.base.base_pagination.BasePaginationFragment
import com.example.presentation.ui.scenes.main.MainActivity
import moxy.presenter.InjectPresenter
import moxy.presenter.ProvidePresenter

class SearchFragment : BasePaginationFragment(), SearchView {

    @InjectPresenter
    override lateinit var presenter: SearchPresenter

    @ProvidePresenter
    fun provideSearchPresenter() = App.appComponent.provideSearchPresenter()

    override val adapter = CardsAdapter(
        { Toast.makeText(context, it, Toast.LENGTH_SHORT).show() },
        {
            val action = SearchFragmentDirections.actionSearchFragmentToFragmentDetailedCard(it)
            findNavController().navigate(action)
        })

    val args: SearchFragmentArgs by navArgs()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val name = args.nameForSearch!!
        presenter.nameForSearch = name
        setHasOptionsMenu(true)

        val actionBar = (activity as MainActivity).supportActionBar
        actionBar?.title = name
        actionBar?.setDisplayHomeAsUpEnabled(true)
        actionBar?.setHomeButtonEnabled(true)
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        super.onCreateOptionsMenu(menu, inflater)
        val search = menu.findItem(R.id.search)
        search.isVisible = false
    }
}