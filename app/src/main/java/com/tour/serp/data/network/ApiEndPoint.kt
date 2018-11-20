package com.tour.serp.data.network

import com.tour.serp.data.network.repository.wrapper.CompanyWrapper
import com.tour.serp.data.network.repository.wrapper.FlightWrapper
import com.tour.serp.data.network.repository.wrapper.HotelWrapper
import kotlinx.coroutines.Deferred
import retrofit2.http.GET

interface ApiEndPoint {
    @GET(companies)
    fun getCompanies(): Deferred<CompanyWrapper>

    @GET(flights)
    fun getFlights(): Deferred<FlightWrapper>

    @GET(hotels)
    fun getHotels(): Deferred<HotelWrapper>

}