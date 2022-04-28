package com.woojoo.kbanktest.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.woojoo.kbanktest.databinding.ItemImageResultBinding
import com.woojoo.kbanktest.model.response.ResImage
import com.woojoo.kbanktest.ui.ViewHolder.ImageResultViewHolder

class ImageResultAdapter(private val response: ResImage): RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return ImageResultViewHolder(ItemImageResultBinding.inflate(LayoutInflater.from(parent.context), parent, false))
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        (holder as ImageResultViewHolder).onBind(response)
    }

    override fun getItemCount(): Int = response.documents.size

}