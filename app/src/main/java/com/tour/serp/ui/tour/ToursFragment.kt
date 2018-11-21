package com.tour.serp.ui.tour

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.databinding.DataBindingUtil
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v7.app.AlertDialog
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.tour.serp.R
import com.tour.serp.data.network.model.Flight
import com.tour.serp.data.network.model.Hotel
import com.tour.serp.data.network.repository.CompanyRepository
import com.tour.serp.data.network.repository.FlightRepository
import com.tour.serp.data.network.repository.HotelRepository
import com.tour.serp.databinding.FragmentToursBinding
import com.tour.serp.databinding.FragmentToursDialogBinding


class ToursFragment : Fragment(), HotelAdapter.HotelAdapterInteraction {
    private val viewModel: ToursViewModel
        get() = ViewModelProviders.of(this)[ToursViewModel::class.java]
    private var hotelAdapter = HotelAdapter(this)

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val binding = DataBindingUtil.inflate<FragmentToursBinding>(inflater, R.layout.fragment_tours, container, false)

        initViewModel()

        binding.hotelList.adapter = hotelAdapter

        return binding.root
    }

    private fun initViewModel() {
        viewModel.init(CompanyRepository(), FlightRepository(), HotelRepository())
        viewModel.hotelsData.observe(this, Observer { it?.let { hotels -> hotelAdapter.addHotels(hotels) } })
    }

    override fun onClickHotel(hotel: Hotel) {
        showAlertDialog(hotel.flightsObject)
    }

    private fun showAlertDialog(flights: List<Flight>) {
        if (context == null) {
            return
        }
        val view = DataBindingUtil.inflate<FragmentToursDialogBinding>(
            LayoutInflater.from(context!!),
            R.layout.fragment_tours_dialog,
            null,
            false
        )
        val tourAdapter = TourAdapter(flights)
        view.toursList.adapter = tourAdapter
        AlertDialog.Builder(context!!)
            .setView(
                view.root
            )
            .setPositiveButton(R.string.apply) { dialog, which ->
                Toast.makeText(context, tourAdapter.getSelectedTour()?.companyObject?.name, Toast.LENGTH_SHORT).show()
            }
            .create()
            .show()
    }

    companion object {
        fun newInstance() = ToursFragment()

        fun startFragment(supportFragmentManager: FragmentManager) {
            supportFragmentManager
                .beginTransaction()
                .replace(android.R.id.content, newInstance())
                .commit()
        }

    }

}