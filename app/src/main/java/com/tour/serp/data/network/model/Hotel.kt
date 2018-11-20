package com.tour.serp.data.network.model

data class Hotel(val id: Int, val flights: List<Int>, val name: String, val price: Int, var flightsObject: List<Flight>)