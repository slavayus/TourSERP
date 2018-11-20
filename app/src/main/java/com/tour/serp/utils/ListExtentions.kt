package com.tour.serp.utils

import com.tour.serp.data.network.model.Company
import com.tour.serp.data.network.model.Flight

fun List<Company>.getById(id: Int): Company? {
    return find { it.id == id }
}

fun List<Flight>.getFlightsById(ids: List<Int>): List<Flight> {
    val flights = mutableListOf<Flight>()
    forEach {
        if (it.id in ids) {
            flights.add(it)
        }
    }
    return flights
}