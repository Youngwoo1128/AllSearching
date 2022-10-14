package com.woojoo.allsearching.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.woojoo.allsearching.databinding.ItemImageResultBinding
import com.woojoo.allsearching.databinding.ItemVideoResultBinding
import com.woojoo.allsearching.domain.entites.Documents
import com.woojoo.allsearching.domain.entites.Researching
import com.woojoo.allsearching.ui.ViewHolder.ImageResultViewHolder
import com.woojoo.allsearching.ui.ViewHolder.VideoResultViewHolder

//class SearchingResultAdapter(private val callback: InsertSearchingData): RecyclerView.Adapter<RecyclerView.ViewHolder>() {
class SearchingResultAdapter(private val callback: InsertSearchingData):
    PagingDataAdapter<Documents, RecyclerView.ViewHolder>(diffCallback) {

//    private val documents = arrayListOf<Documents>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return if (viewType == IMAGE_VIEW_TYPE) {
            val binding = ItemImageResultBinding.inflate(LayoutInflater.from(parent.context), parent, false)
             ImageResultViewHolder(binding)
        }else {
            val binding = ItemVideoResultBinding.inflate(LayoutInflater.from(parent.context), parent, false)
            VideoResultViewHolder(binding)
        }

    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
//        val item = documents[position]
        val item = getItem(position)

        if (item?.viewType == IMAGE_VIEW_TYPE) {
            (holder as ImageResultViewHolder).onBind(item, callback)
        } else {
            (holder as VideoResultViewHolder).onBind(item, callback)
        }
    }

//    override fun getItemCount(): Int = documents.size

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
    }

//    fun addNewItem(newItem: ArrayList<Documents>) {
//        documents.addAll(newItem)
//        documents.distinct()
//    }
//
//    fun clearSearchingResult() {
//        getItem().clear()
//    }

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