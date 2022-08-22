package com.woojoo.allsearching.ui.ViewHolder

import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.woojoo.allsearching.R
import com.woojoo.allsearching.databinding.ItemImageResultBinding
import com.woojoo.allsearching.model.response.Document

class ImageResultViewHolder(val binding: ItemImageResultBinding): RecyclerView.ViewHolder(binding.root) {

    fun onBind(item: ArrayList<Document>, position: Int) {
        binding.item = item[position]

        Glide.with(binding.root.context)
            .load(item[position].thumbnail_url)
            .placeholder(R.drawable.default_image)
            .circleCrop()
            .into(binding.ivThumnail)

//        binding.root.setOnClickListener {}
    }
}