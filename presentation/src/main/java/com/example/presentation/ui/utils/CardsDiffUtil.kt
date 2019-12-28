package com.example.presentation.ui.utils

import androidx.recyclerview.widget.DiffUtil
import com.example.domain.entities.PokemonCard

class CardsDiffUtil(
    private var oldList: List<PokemonCard>,
    private var newList: List<PokemonCard>
) : DiffUtil.Callback() {

    override fun getOldListSize(): Int {
        return oldList.size
    }

    override fun getNewListSize(): Int {
        return newList.size
    }

    override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        return oldList[oldItemPosition].id == newList[newItemPosition].id
    }

    override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        return false
    }
}