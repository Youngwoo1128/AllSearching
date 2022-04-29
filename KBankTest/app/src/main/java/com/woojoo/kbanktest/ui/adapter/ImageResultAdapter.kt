package com.woojoo.kbanktest.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.woojoo.kbanktest.databinding.ItemImageResultBinding
import com.woojoo.kbanktest.model.network.response.Documents
import com.woojoo.kbanktest.ui.ViewHolder.ImageResultViewHolder

class ImageResultAdapter: RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    var document = ArrayList<Documents>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return ImageResultViewHolder(ItemImageResultBinding.inflate(LayoutInflater.from(parent.context), parent, false))
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        (holder as ImageResultViewHolder).onBind(document, position)
    }

    override fun getItemCount(): Int = document.size

    fun setItemList(items: ArrayList<Documents>) {
        this.document = items
        notifyDataSetChanged()
    }
}