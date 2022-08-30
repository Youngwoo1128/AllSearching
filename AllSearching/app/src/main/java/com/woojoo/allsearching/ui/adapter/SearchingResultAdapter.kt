package com.woojoo.allsearching.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.woojoo.allsearching.databinding.ItemImageResultBinding
import com.woojoo.allsearching.databinding.ItemVideoResultBinding
import com.woojoo.allsearching.model.response.Document
import com.woojoo.allsearching.ui.ViewHolder.ImageResultViewHolder
import com.woojoo.allsearching.ui.ViewHolder.VideoResultViewHolder

class SearchingResultAdapter: RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    var documents = arrayListOf<Document>()

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
        val item = documents[position]

        if (item.viewType == IMAGE_VIEW_TYPE) {
            (holder as ImageResultViewHolder).onBind(documents, position)
        } else {
            (holder as VideoResultViewHolder).onBind(documents, position)
        }
    }

    override fun getItemCount(): Int = documents.size

    override fun getItemViewType(position: Int): Int {
        if (!documents[position].thumbnail_url.isNullOrEmpty() && documents[position].thumbnail.isNullOrEmpty()) {
            documents[position].viewType = IMAGE_VIEW_TYPE
            return IMAGE_VIEW_TYPE
        }else {
            documents[position].viewType = VIDEO_VIEW_TYPE
            return VIDEO_VIEW_TYPE
        }
    }

//    interface ConvertDate {
//        fun convertDate(date: String): String
//    }

    companion object {
        private const val IMAGE_VIEW_TYPE = 1
        private const val VIDEO_VIEW_TYPE = 2
    }
}