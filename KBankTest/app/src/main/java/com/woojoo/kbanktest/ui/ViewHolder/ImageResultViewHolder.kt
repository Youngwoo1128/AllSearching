package com.woojoo.kbanktest.ui.ViewHolder

import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.woojoo.kbanktest.R
import com.woojoo.kbanktest.databinding.ItemImageResultBinding
import com.woojoo.kbanktest.model.response.Document
import com.woojoo.kbanktest.ui.adapter.ImageResultAdapter

class ImageResultViewHolder(val binding: ItemImageResultBinding): RecyclerView.ViewHolder(binding.root) {

    fun onBind(item: ArrayList<Document>, position: Int, callback: ImageResultAdapter.ConvertDate) {
        binding.item = item[position]
        binding.callback = callback

        Glide.with(binding.root.context)
            .load(item[position].thumbnail_url)
            .placeholder(R.drawable.ic_launcher_background)
            .circleCrop()
            .into(binding.ivThumnail)

//        binding.root.setOnClickListener {}
    }
}