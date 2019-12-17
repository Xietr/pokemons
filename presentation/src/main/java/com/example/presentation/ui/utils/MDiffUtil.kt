package com.example.presentation.ui.utils

import androidx.recyclerview.widget.DiffUtil

class MDiffUtil<T>(
    private var oldList: ArrayList<T>,
    private var newList: ArrayList<T>
) : DiffUtil.Callback() {

    override fun getOldListSize(): Int {
        return oldList.size
    }

    override fun getNewListSize(): Int {
        return newList.size
    }

    override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        return false
    }

    override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        return false
    }
}