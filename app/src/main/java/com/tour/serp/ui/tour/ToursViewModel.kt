package com.tour.serp.ui.tour

import android.arch.lifecycle.ViewModel
import com.tour.serp.data.network.repository.CompanyRepository
import com.tour.serp.utils.debug
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch

class ToursViewModel : ViewModel() {
    private lateinit var companyRepository: CompanyRepository
    private val viewModelJob = Job()
    private val uiScope = CoroutineScope(Dispatchers.Main + viewModelJob)

    fun init(companyRepository: CompanyRepository) {
        this.companyRepository = companyRepository
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
                debug(ToursViewModel::class, companies)
            } catch (e: Exception) {
            }
        }
    }
}