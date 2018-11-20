package com.tour.serp.data.network.repository

import com.tour.serp.App
import com.tour.serp.data.network.model.Hotel

class HotelRepository {
    suspend fun getHotels(): List<Hotel> {
        return App.api.getHotels().await().hotels
    }
}