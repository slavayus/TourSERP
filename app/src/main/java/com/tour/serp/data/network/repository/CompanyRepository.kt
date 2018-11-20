package com.tour.serp.data.network.repository

import com.tour.serp.App
import com.tour.serp.data.network.model.Company

class CompanyRepository {
    suspend fun getCompanies(): List<Company> {
        return App.api.getCompanies().await().companies
    }
}