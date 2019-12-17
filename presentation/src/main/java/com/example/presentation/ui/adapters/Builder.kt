package com.example.presentation.ui.adapters

import androidx.recyclerview.widget.RecyclerView

interface Builder {
    fun setType(type: Any)
    fun setViewHolder(holder: RecyclerView.ViewHolder)
}