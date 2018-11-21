package com.tour.serp.ui.tour

import android.databinding.DataBindingUtil
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup
import com.tour.serp.R
import com.tour.serp.data.network.model.Flight
import com.tour.serp.databinding.FragmentToursDialogRecyclerViewTourItemBinding
import com.tour.serp.utils.debug

class TourAdapter(val data: List<Flight>) : RecyclerView.Adapter<TourAdapter.TourHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TourHolder {
        val binding = DataBindingUtil.inflate<FragmentToursDialogRecyclerViewTourItemBinding>(
            LayoutInflater.from(parent.context),
            R.layout.fragment_tours_dialog_recycler_view_tour_item,
            parent,
            false
        )
        return TourHolder(binding)
    }

    override fun getItemCount() = data.size

    override fun onBindViewHolder(holder: TourHolder, position: Int) = holder.bind(data[position])

    inner class TourHolder(private val binding: FragmentToursDialogRecyclerViewTourItemBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(flight: Flight) {
            debug(TourHolder::class, flight)
            binding.flight = flight
        }

    }
}