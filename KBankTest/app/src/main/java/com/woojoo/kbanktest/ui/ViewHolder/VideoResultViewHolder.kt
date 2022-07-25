package com.woojoo.kbanktest.ui.ViewHolder

import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.woojoo.kbanktest.R
import com.woojoo.kbanktest.databinding.ItemVideoResultBinding
import com.woojoo.kbanktest.model.response.Document
import com.woojoo.kbanktest.ui.adapter.SearchingResultAdapter

class VideoResultViewHolder(val binding: ItemVideoResultBinding): RecyclerView.ViewHolder(binding.root) {
    fun onBind(item: ArrayList<Document>, position: Int) {
        binding.item = item[position]

        Glide.with(binding.root.context)
            .load(item[position].thumbnail)
            .placeholder(R.drawable.default_image)
            .circleCrop()
            .into(binding.ivThumnail)

//        binding.root.setOnClickListener {}
    }
}