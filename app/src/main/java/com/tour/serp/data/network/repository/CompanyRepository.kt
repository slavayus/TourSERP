package com.tour.serp.data.network.repository

import com.tour.serp.data.network.model.Company

interface CompanyRepository {
    suspend fun getCompanies(): List<Company>
}