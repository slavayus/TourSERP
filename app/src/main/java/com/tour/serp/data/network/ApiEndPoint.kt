package com.tour.serp.data.network

import com.tour.serp.data.network.repository.wrapper.CompanyWrapper
import kotlinx.coroutines.Deferred
import retrofit2.http.GET

interface ApiEndPoint {
    @GET(companies)
    fun getCompanies(): Deferred<CompanyWrapper>

}