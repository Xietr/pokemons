package com.example.presentation.ui.listeners

import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView

class PaginationScrollListener(private val pageSize: Int = 10, private val onLoadMore: () -> Unit) :
    RecyclerView.OnScrollListener() {

    override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
        val gridLayoutManager = recyclerView.layoutManager as GridLayoutManager

        val visibleItemCount = gridLayoutManager.childCount
        val totalItemCount = gridLayoutManager.itemCount
        val firstVisibleItemPosition = gridLayoutManager.findFirstVisibleItemPosition()
        if ((visibleItemCount + firstVisibleItemPosition) >= totalItemCount
            && firstVisibleItemPosition >= 0
            && totalItemCount >= pageSize
        ) {
            onLoadMore.invoke()
            gridLayoutManager.scrollToPosition(totalItemCount)
        }
    }
}