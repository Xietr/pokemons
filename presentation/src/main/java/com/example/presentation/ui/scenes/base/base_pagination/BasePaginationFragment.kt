package com.example.presentation.ui.scenes.base.base_pagination

import android.os.Bundle
import android.util.TypedValue
import android.view.View
import android.widget.Toast
import androidx.recyclerview.widget.GridLayoutManager
import com.example.domain.entities.PokemonCard
import com.example.presentation.R
import com.example.presentation.ui.adapters.CardsAdapter
import com.example.presentation.ui.decorators.GridItemDecoration
import com.example.presentation.ui.listeners.PaginationScrollListener
import kotlinx.android.synthetic.main.fragment_base_cards.*
import moxy.MvpAppCompatFragment
import kotlin.math.roundToInt

abstract class BasePaginationFragment : MvpAppCompatFragment(R.layout.fragment_base_cards),
    BasePaginationView {

    abstract val presenter: BasePaginationPresenter<*>

    abstract val adapter: CardsAdapter


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupRefreshLayout()
        setupRecyclerView()
    }

    private fun setupRefreshLayout() {
        cardsSwipeLayout.setOnRefreshListener {
            presenter.onRefresh()
        }
    }

    private fun setupRecyclerView() {
        cardsRecyclerView.apply {
            layoutManager = GridLayoutManager(context, 2)
            adapter = this@BasePaginationFragment.adapter

            val px = TypedValue.applyDimension(
                TypedValue.COMPLEX_UNIT_DIP, 16f, resources.displayMetrics
            ).roundToInt()//dp in pixels
            addItemDecoration(GridItemDecoration(px))

            addOnScrollListener(PaginationScrollListener(onLoadMore = presenter::getCards))
//            itemAnimator = null
        }
    }

    override fun clearAdapter() {
        cardsRecyclerView.recycledViewPool.clear()
        adapter.clear()
        adapter.notifyDataSetChanged()//without it -> java.lang.IndexOutOfBoundsException: Inconsistency detected.
        // Invalid item position 6(offset:6).state:10 androidx.recyclerview.widget.RecyclerView
    }

    override fun setRecyclerViewItems(list: List<PokemonCard>) {
        adapter.setCards(list)
    }

    override fun setIsRefreshing(isVisible: Boolean) {
        cardsSwipeLayout.isRefreshing = isVisible
    }

    override fun showToast(message: String) {
        Toast.makeText(context, message, Toast.LENGTH_SHORT).show()
    }

    override fun setIsProgressBarVisible(isVisible: Boolean) {
        if (isVisible) {
            progressBar.visibility = View.VISIBLE
        } else {
            progressBar.visibility = View.GONE
        }
    }
}