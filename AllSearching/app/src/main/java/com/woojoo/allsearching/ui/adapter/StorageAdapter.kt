package com.woojoo.allsearching.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.woojoo.allsearching.databinding.ItemStorageBinding
import com.woojoo.allsearching.domain.entites.Researching
import com.woojoo.allsearching.ui.viewholders.StorageViewHolder

class StorageAdapter(private val callback: DeleteLocalItem) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    var researchingList = mutableListOf<Researching>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val binding = ItemStorageBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return StorageViewHolder(binding)
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val item = researchingList[position]
        (holder as? StorageViewHolder)?.onBind(item, callback)
    }

    override fun getItemCount(): Int = researchingList.size

    fun removeItem(index: Int) {
        researchingList.removeAt(index)
        notifyItemRemoved(index)
    }

    fun addDBList(newItem: List<Researching>) {
        researchingList.addAll(newItem)
        researchingList = researchingList.distinct().toMutableList()
        notifyDataSetChanged()
    }

    interface DeleteLocalItem {
        fun deleteLocalItem(item: Researching)
    }

}
