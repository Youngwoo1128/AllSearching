package com.woojoo.allsearching.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.woojoo.allsearching.databinding.ItemStorageBinding
import com.woojoo.allsearching.domain.entites.Researching
import com.woojoo.allsearching.ui.ViewHolder.StorageViewHolder

class StorageAdapter : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    private val researchingList = ArrayList<Researching>()


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val binding = ItemStorageBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return StorageViewHolder(binding)
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val item = researchingList[position]
        (holder as StorageViewHolder).onBind(item)
    }

    fun addNewItem(newItem: List<Researching>) {
        researchingList.addAll(newItem)
        researchingList.distinct()
        notifyDataSetChanged()
    }

    override fun getItemCount(): Int = researchingList.size
}