package com.tour.serp.ui.tour

import android.databinding.DataBindingUtil
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup
import com.tour.serp.R
import com.tour.serp.data.network.model.Hotel
import com.tour.serp.databinding.FragmentToursRecyclerViewHotelBinding

class HotelAdapter(val interaction: HotelAdapterInteraction) : RecyclerView.Adapter<HotelAdapter.HotelHolder>() {
    private val data = ArrayList<Hotel>()

    interface HotelAdapterInteraction {
        fun onClickHotel(hotel: Hotel)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HotelHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = DataBindingUtil.inflate<FragmentToursRecyclerViewHotelBinding>(
            inflater,
            R.layout.fragment_tours_recycler_view_hotel,
            parent,
            false
        )
        return HotelHolder(binding)
    }

    override fun getItemCount() = data.size

    override fun onBindViewHolder(holder: HotelHolder, position: Int) = holder.bind(data[position])

    fun addHotels(hotels: List<Hotel>) {
        val oldSize = data.size
        this.data.addAll(hotels)
        notifyItemRangeInserted(oldSize, data.size)
    }

    inner class HotelHolder(var binding: FragmentToursRecyclerViewHotelBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(hotel: Hotel) {
            binding.hotel = hotel
            itemView.setOnClickListener { interaction.onClickHotel(hotel) }
        }

    }

}