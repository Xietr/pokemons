package com.example.presentation.ui.adapters

import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.presentation.ui.utils.MDiffUtil

class MRecyclerViewAdapter(builder: Builder) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    private var listOfTypes = arrayListOf<Any>()
    private var adapterList = arrayListOf<Any>()

    fun setNewList(list: ArrayList<Any>) {
        renderNewList(list)
        adapterList = list
    }

    fun clear() {
        renderNewList(ArrayList())
        adapterList = ArrayList()
    }

    private fun renderNewList(list: ArrayList<Any>) {
        val mDiffUtil = MDiffUtil(adapterList, list)
        val diffResult = DiffUtil.calculateDiff(mDiffUtil)
        diffResult.dispatchUpdatesTo(this)
    }

    override fun getItemCount(): Int {
        return adapterList.size
    }

    override fun getItemViewType(position: Int): Int {
        for (type in 0..listOfTypes.count()) {
            return type
        }
        return super.getItemViewType(position)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    abstract class Builder {
        private var listOfTypes = arrayListOf<Any>()

        fun addType(type: Any) {
            listOfTypes.add(type)
        }

        fun setViewHolder(holder: RecyclerView.ViewHolder) {

        }

        fun build() = MRecyclerViewAdapter()
    }
}