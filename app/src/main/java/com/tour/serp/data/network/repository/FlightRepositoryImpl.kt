package com.tour.serp.data.network.repository

import com.tour.serp.App
import com.tour.serp.data.network.model.Flight

class FlightRepositoryImpl : FlightRepository {
    override suspend fun getFlights(): List<Flight> {
        return App.api.getFlights().await().flights
    }
}