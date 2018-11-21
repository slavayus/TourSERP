package com.tour.serp.ui.tour

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.databinding.DataBindingUtil
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v7.app.AlertDialog
import android.support.v7.util.DiffUtil
import android.support.v7.widget.SearchView
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
import com.tour.serp.ui.tour.adapter.FlightAdapter
import com.tour.serp.ui.tour.adapter.HotelAdapter
import com.tour.serp.ui.tour.adapter.diff.FlightsDiffUtil


class ToursFragment : Fragment(), HotelAdapter.HotelAdapterInteraction {
    private val viewModel: ToursViewModel
        get() = ViewModelProviders.of(this)[ToursViewModel::class.java]
    private var hotelAdapter = HotelAdapter(this)

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val binding = DataBindingUtil.inflate<FragmentToursBinding>(inflater, R.layout.fragment_tours, container, false)

        initViewModel()

        binding.hotelList.adapter = hotelAdapter

        binding.searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(p0: String?): Boolean {
                return true
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                newText?.let { viewModel.onTextChangedInSearchView(it) }
                return true
            }

        })

        return binding.root
    }

    private fun initViewModel() {
        viewModel.init(CompanyRepository(), FlightRepository(), HotelRepository())
        viewModel.hotelsData.observe(this, Observer { it?.let { hotels -> updateHotels(hotels) } })
    }

    private fun updateHotels(hotels: List<Hotel>) {
        val flightsDiffUtil = FlightsDiffUtil(hotelAdapter.data, hotels)
        val productDiffResult = DiffUtil.calculateDiff(flightsDiffUtil)
        hotelAdapter.setHotels(hotels)
        productDiffResult.dispatchUpdatesTo(hotelAdapter)
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
        val tourAdapter = FlightAdapter(flights)
        view.toursList.adapter = tourAdapter
        AlertDialog.Builder(context!!)
            .setView(
                view.root
            )
            .setPositiveButton(R.string.apply) { _, _ ->
                Toast.makeText(context, tourAdapter.getSelectedFlight()?.companyObject?.name, Toast.LENGTH_SHORT).show()
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