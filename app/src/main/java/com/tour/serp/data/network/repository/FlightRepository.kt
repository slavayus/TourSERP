package com.tour.serp.data.network.repository

import com.tour.serp.App
import com.tour.serp.data.network.model.Flight

interface FlightRepository {
    suspend fun getFlights(): List<Flight>
}