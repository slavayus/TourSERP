package com.tour.serp.ui.tour.adapter

import android.databinding.DataBindingUtil
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup
import com.tour.serp.R
import com.tour.serp.data.network.model.Hotel
import com.tour.serp.databinding.FragmentToursRecyclerViewHotelBinding

class HotelAdapter(val interaction: HotelAdapterInteraction) : RecyclerView.Adapter<HotelAdapter.HotelHolder>() {
    var data: List<Hotel> = ArrayList()

    interface HotelAdapterInteraction {
        fun onClickHotel(hotel: Hotel)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HotelHolder {
        val binding = DataBindingUtil.inflate<FragmentToursRecyclerViewHotelBinding>(
            LayoutInflater.from(parent.context),
            R.layout.fragment_tours_recycler_view_hotel,
            parent,
            false
        )
        return HotelHolder(binding)
    }

    override fun getItemCount() = data.size

    override fun onBindViewHolder(holder: HotelHolder, position: Int) = holder.bind(data[position])

    fun setHotels(hotels: List<Hotel>) {
        this.data = hotels
    }

    inner class HotelHolder(var binding: FragmentToursRecyclerViewHotelBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(hotel: Hotel) {
            binding.hotel = hotel
            itemView.setOnClickListener { interaction.onClickHotel(hotel) }
        }

    }

}