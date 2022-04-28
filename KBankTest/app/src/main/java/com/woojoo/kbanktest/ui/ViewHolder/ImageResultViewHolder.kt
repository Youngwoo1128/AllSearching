package com.woojoo.kbanktest.ui.ViewHolder

import androidx.recyclerview.widget.RecyclerView
import com.woojoo.kbanktest.databinding.ItemImageResultBinding
import com.woojoo.kbanktest.model.response.ResImage

class ImageResultViewHolder(val binding: ItemImageResultBinding): RecyclerView.ViewHolder(binding.root) {
    fun onBind(item: ResImage) {
//        binding.item = item.documents
    }
}

