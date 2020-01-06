package com.example.presentation.ui.scenes.base.base_pagination_with_search

import android.os.Bundle
import android.view.Menu
import android.view.MenuInflater
import android.view.View
import android.widget.Toast
import androidx.appcompat.widget.SearchView
import com.example.presentation.R
import com.example.presentation.ui.scenes.base.base_pagination.BasePaginationFragment
import com.example.presentation.ui.scenes.main.MainActivity

abstract class BasePaginationSearchFragment : BasePaginationFragment(), BasePaginationSearchView {

    abstract val onSearch: (query: String?) -> Unit

    abstract val title: String


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setHasOptionsMenu(true)

        val actionBar = (activity as MainActivity).supportActionBar
        actionBar?.title = title
        actionBar?.setDisplayHomeAsUpEnabled(false)
        actionBar?.setHomeButtonEnabled(false)
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        super.onCreateOptionsMenu(menu, inflater)

        val search = menu.findItem(R.id.search)
        val searchView = search.actionView as SearchView
        searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {

            override fun onQueryTextSubmit(query: String?): Boolean {
                onSearch.invoke(query)
                return false
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                return false
            }
        })
    }
}