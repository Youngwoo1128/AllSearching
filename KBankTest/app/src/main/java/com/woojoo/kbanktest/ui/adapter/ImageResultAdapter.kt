package com.woojoo.kbanktest.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.woojoo.kbanktest.databinding.ItemImageResultBinding
import com.woojoo.kbanktest.model.response.Document
import com.woojoo.kbanktest.ui.ViewHolder.ImageResultViewHolder

class ImageResultAdapter: RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    var documents = arrayListOf<Document>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return ImageResultViewHolder(ItemImageResultBinding.inflate(LayoutInflater.from(parent.context), parent, false))
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        (holder as ImageResultViewHolder).onBind(documents, position, object : ConvertDate {
            override fun convertDate(date: String): String {
              return date.substring(0, 10)
            }
        })
    }

    override fun getItemCount(): Int = documents.size

    interface ConvertDate {
        fun convertDate(date: String): String
    }
}