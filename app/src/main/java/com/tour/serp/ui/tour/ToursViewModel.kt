package com.tour.serp.ui.tour

import android.arch.lifecycle.ViewModel
import com.tour.serp.data.network.repository.CompanyRepository
import com.tour.serp.data.network.repository.FlightRepository
import com.tour.serp.data.network.repository.HotelRepository
import com.tour.serp.utils.debug
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch

class ToursViewModel : ViewModel() {
    private val viewModelJob = Job()
    private val uiScope = CoroutineScope(Dispatchers.Main + viewModelJob)

    private lateinit var companyRepository: CompanyRepository
    private lateinit var flightRepository: FlightRepository
    private lateinit var hotelRepository: HotelRepository

    fun init(
        companyRepository: CompanyRepository,
        flightRepository: FlightRepository,
        hotelRepository: HotelRepository
    ) {
        this.companyRepository = companyRepository
        this.flightRepository = flightRepository
        this.hotelRepository = hotelRepository
        loadTours()
    }

    override fun onCleared() {
        super.onCleared()
        viewModelJob.cancel()
    }

    private fun loadTours() {
        uiScope.launch {
            try {
                val companies = companyRepository.getCompanies()
                val flights = flightRepository.getFlights()
                val hotels = hotelRepository.getHotels()
                debug(ToursViewModel::class, companies)
                debug(ToursViewModel::class, flights)
                debug(ToursViewModel::class, hotels)
            } catch (e: Exception) {
            }
        }
    }
}