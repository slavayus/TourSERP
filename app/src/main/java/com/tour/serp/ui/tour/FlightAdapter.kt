package com.tour.serp.ui.tour

import android.databinding.DataBindingUtil
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup
import com.tour.serp.R
import com.tour.serp.data.network.model.Flight
import com.tour.serp.databinding.FragmentToursDialogRecyclerViewTourItemBinding
import com.tour.serp.utils.debug

class FlightAdapter(val data: List<Flight>) : RecyclerView.Adapter<FlightAdapter.FlightHolder>() {
    private var lastClicked: Int = -1

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FlightHolder {
        val binding = DataBindingUtil.inflate<FragmentToursDialogRecyclerViewTourItemBinding>(
            LayoutInflater.from(parent.context),
            R.layout.fragment_tours_dialog_recycler_view_tour_item,
            parent,
            false
        )
        return FlightHolder(binding)
    }

    override fun getItemCount() = data.size

    override fun onBindViewHolder(holder: FlightHolder, position: Int) = holder.bind(data[position])

    fun getSelectedFlight() =
        if (lastClicked != -1) {
            data[lastClicked]
        } else {
            null
        }

    inner class FlightHolder(private val binding: FragmentToursDialogRecyclerViewTourItemBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(flight: Flight) {
            debug(FlightHolder::class, flight)
            binding.flight = flight
            binding.tourName.isChecked = lastClicked == layoutPosition
            binding.tourName.setOnClickListener {
                clickHandler()
            }
            itemView.setOnClickListener {
                clickHandler()
            }
        }

        private fun clickHandler() {
            val temp = lastClicked
            lastClicked = layoutPosition
            notifyItemChanged(temp)
            notifyItemChanged(lastClicked)
        }
    }
}