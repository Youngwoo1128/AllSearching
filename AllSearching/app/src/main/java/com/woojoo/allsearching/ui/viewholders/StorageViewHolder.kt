package com.woojoo.allsearching.ui.viewholders

import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.woojoo.allsearching.R
import com.woojoo.allsearching.databinding.ItemStorageBinding
import com.woojoo.allsearching.domain.entites.Researching
import com.woojoo.allsearching.ui.adapter.StorageAdapter

class StorageViewHolder(val binding: ItemStorageBinding): RecyclerView.ViewHolder(binding.root) {

    fun onBind(item: Researching, callback: StorageAdapter.DeleteLocalItem) {
        binding.item = item
        binding.callback = callback

        Glide.with(binding.root.context)
            .load(item.thumbnail)
            .placeholder(R.drawable.default_image)
            .circleCrop()
            .into(binding.imageViewThumnail)
    }

}