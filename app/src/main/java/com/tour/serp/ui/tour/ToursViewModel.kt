package com.tour.serp.ui.tour

import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.ViewModel
import com.tour.serp.data.network.ApiExceptions
import com.tour.serp.data.network.model.Company
import com.tour.serp.data.network.model.Flight
import com.tour.serp.data.network.model.Hotel
import com.tour.serp.data.network.repository.CompanyRepository
import com.tour.serp.data.network.repository.FlightRepository
import com.tour.serp.data.network.repository.HotelRepository
import com.tour.serp.utils.getById
import com.tour.serp.utils.getFlightsById
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch

class ToursViewModel : ViewModel(), ApiExceptions {
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
                handleResults(companies, flights, hotels)
            } catch (e: Exception) {
                handleException(e)
            }
        }
    }

    private fun handleResults(companies: List<Company>, flights: List<Flight>, hotels: List<Hotel>) {
        combineFlightsWithCompanies(companies, flights)
        combineFlightsWithHotels(flights, hotels)
    }

    private fun combineFlightsWithCompanies(companies: List<Company>, flights: List<Flight>) {
        flights.forEach { it.companyObject = companies.getById(it.id) }
    }

    private fun combineFlightsWithHotels(flights: List<Flight>, hotels: List<Hotel>) {
        hotels.forEach { it.flightsObject = flights.getFlightsById(it.flights) }
    }
}