package com.example.presentation.ui.adapters

import androidx.recyclerview.widget.RecyclerView

class AdapterBuilder : Builder {

    private var typeCount = 0
    private val type = arrayListOf<Any>()
    private val listOfViewHolders = arrayListOf<RecyclerView.ViewHolder>()


    override fun setType(type: Any) {
        this.type.add(type)
        updateTypesCount()
    }

    override fun setViewHolder(holder: RecyclerView.ViewHolder) {
        listOfViewHolders.add(holder)
    }

    private fun updateTypesCount() {
        typeCount = type.size
    }
}