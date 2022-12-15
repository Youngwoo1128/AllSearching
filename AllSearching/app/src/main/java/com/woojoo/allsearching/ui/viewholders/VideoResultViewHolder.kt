package com.woojoo.allsearching.ui.viewholders

import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.woojoo.allsearching.R
import com.woojoo.allsearching.databinding.ItemVideoResultBinding
import com.woojoo.allsearching.domain.entites.Documents
import com.woojoo.allsearching.ui.adapter.SearchingResultAdapter

class VideoResultViewHolder(val binding: ItemVideoResultBinding): RecyclerView.ViewHolder(binding.root) {
    fun onBind(item: Documents?, callback: SearchingResultAdapter.InsertSearchingData) {
        binding.item = item

        Glide.with(binding.root.context)
            .load(item?.thumbnail)
            .placeholder(R.drawable.default_image)
            .circleCrop()
            .into(binding.imageViewThumnail)

        binding.layoutVideoResult.setOnClickListener {
            item?.let { result ->
                callback.onClick(result)
            }
        }

        binding.ivFavorite.setOnClickListener {
            item?.let {
                callback.onInsertSearchingData(it)
            }
        }
    }
}