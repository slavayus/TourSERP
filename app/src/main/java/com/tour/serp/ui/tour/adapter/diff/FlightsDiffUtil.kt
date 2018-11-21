package com.tour.serp.ui.tour.adapter.diff

import android.support.v7.util.DiffUtil
import com.tour.serp.data.network.model.Hotel


class FlightsDiffUtil(private val oldList: List<Hotel>, private val newList: List<Hotel>) : DiffUtil.Callback() {

    override fun getOldListSize() = oldList.size

    override fun getNewListSize() = newList.size

    override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        return oldList[oldItemPosition].id == newList[newItemPosition].id
    }

    override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        return oldList[oldItemPosition].id == newList[newItemPosition].id
    }

}
