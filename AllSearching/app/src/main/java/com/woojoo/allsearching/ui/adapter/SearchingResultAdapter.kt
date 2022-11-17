package com.woojoo.allsearching.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.woojoo.allsearching.databinding.ItemImageResultBinding
import com.woojoo.allsearching.databinding.ItemVideoResultBinding
import com.woojoo.allsearching.domain.entites.Documents
import com.woojoo.allsearching.ui.viewholders.ImageResultViewHolder
import com.woojoo.allsearching.ui.viewholders.VideoResultViewHolder

class SearchingResultAdapter(
    private val callback: InsertSearchingData
) : PagingDataAdapter<Documents, RecyclerView.ViewHolder>(diffCallback) {

    private val searchingEvent = callback

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return when(viewType) {
            IMAGE_VIEW_TYPE -> {
                val binding = ItemImageResultBinding.inflate(LayoutInflater.from(parent.context), parent, false)
                ImageResultViewHolder(binding)
            }
            else -> {
                val binding = ItemVideoResultBinding.inflate(LayoutInflater.from(parent.context), parent, false)
                VideoResultViewHolder(binding)
            }
        }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val item = getItem(position)

        if (item?.viewType == IMAGE_VIEW_TYPE) {
            (holder as? ImageResultViewHolder)?.onBind(item, searchingEvent)
        } else {
            (holder as? VideoResultViewHolder)?.onBind(item, searchingEvent)
        }
    }

    override fun getItemViewType(position: Int): Int {
        val item = getItem(position)
        return if (item?.viewType == IMAGE_VIEW_TYPE) {
            IMAGE_VIEW_TYPE
        } else {
            VIDEO_VIEW_TYPE
        }
    }

    interface InsertSearchingData {
        fun onInsertSearchingData(item: Documents)
        fun onClick(item: Documents)
    }

    companion object {
        private const val IMAGE_VIEW_TYPE = 1
        private const val VIDEO_VIEW_TYPE = 2

        private val diffCallback = object : DiffUtil.ItemCallback<Documents>() {
            override fun areItemsTheSame(oldItem: Documents, newItem: Documents): Boolean {
                return oldItem.title == newItem.title
            }

            override fun areContentsTheSame(oldItem: Documents, newItem: Documents): Boolean {
                return oldItem == newItem
            }

        }
    }
}