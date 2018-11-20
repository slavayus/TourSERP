package com.tour.serp.ui.tour

import android.arch.lifecycle.ViewModel
import com.tour.serp.data.network.repository.CompanyRepository
import com.tour.serp.data.network.repository.FlightRepository
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

    fun init(
        companyRepository: CompanyRepository,
        flightRepository: FlightRepository
    ) {
        this.companyRepository = companyRepository
        this.flightRepository = flightRepository
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
                val flight = flightRepository.getFlight()
                debug(ToursViewModel::class, companies)
                debug(ToursViewModel::class, flight)
            } catch (e: Exception) {
            }
        }
    }
}