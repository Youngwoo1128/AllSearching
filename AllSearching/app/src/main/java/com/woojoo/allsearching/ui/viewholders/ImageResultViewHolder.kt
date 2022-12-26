package com.woojoo.allsearching.ui.viewholders

import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.woojoo.allsearching.R
import com.woojoo.allsearching.databinding.ItemImageResultBinding
import com.woojoo.allsearching.domain.entites.Documents
import com.woojoo.allsearching.ui.adapter.SearchingResultAdapter

class ImageResultViewHolder(val binding: ItemImageResultBinding): RecyclerView.ViewHolder(binding.root) {

    fun onBind(item: Documents, callback: SearchingResultAdapter.InsertSearchingData) {
        binding.item = item
        binding.callback = callback

        Glide.with(binding.root.context)
            .load(item.thumbnail)
            .placeholder(R.drawable.default_image)
            .circleCrop()
            .into(binding.imageViewThumnail)

        binding.ivFavorite.setOnClickListener {
            callback.onInsertSearchingData(item)
        }
    }
}