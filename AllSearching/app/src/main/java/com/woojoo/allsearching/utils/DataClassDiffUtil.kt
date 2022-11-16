package com.woojoo.allsearching.utils

import androidx.recyclerview.widget.DiffUtil

/**
 * Class: DataClassDiffUtil
 * Created by ywsong on 2022/06/23.
 * Description:
 */
class DataClassDiffUtil<T>(private val oldList: ArrayList<T>, private val newList: ArrayList<T>): DiffUtil.Callback() {

    override fun getOldListSize(): Int = oldList.size

    override fun getNewListSize(): Int = newList.size

    override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        return oldList[oldItemPosition] === newList[newItemPosition]
    }

    override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        return oldList[oldItemPosition] === newList[newItemPosition]
    }

    companion object {
        inline fun <reified T> create(oldList: ArrayList<T>, newList: ArrayList<T>): DataClassDiffUtil<T> {
            if (!T::class.isData) throw IllegalArgumentException("Use Data Class only")
            return DataClassDiffUtil(oldList, newList)
        }
    }

}