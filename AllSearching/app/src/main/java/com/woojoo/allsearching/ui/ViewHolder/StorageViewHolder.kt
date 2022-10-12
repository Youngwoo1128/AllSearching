package com.woojoo.allsearching.ui.ViewHolder

import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.woojoo.allsearching.R
import com.woojoo.allsearching.databinding.ItemStorageBinding
import com.woojoo.allsearching.domain.entites.Researching

class StorageViewHolder(val binding: ItemStorageBinding): RecyclerView.ViewHolder(binding.root) {

    fun onBind(item: Researching) {
        Glide.with(binding.root.context)
            .load(item.thumbnail)
            .placeholder(R.drawable.default_image)
            .circleCrop()
            .into(binding.ivThumnail)
    }
}