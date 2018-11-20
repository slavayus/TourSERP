package com.tour.serp.ui.tour

import android.arch.lifecycle.ViewModelProviders
import android.databinding.DataBindingUtil
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.tour.serp.R
import com.tour.serp.data.network.repository.CompanyRepository
import com.tour.serp.databinding.FragmentToursBinding

class ToursFragment : Fragment() {
    private val viewModel: ToursViewModel
        get() = ViewModelProviders.of(this)[ToursViewModel::class.java]

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val binding = DataBindingUtil.inflate<FragmentToursBinding>(inflater, R.layout.fragment_tours, container, false)

        initViewModel()

        return binding.root
    }

    private fun initViewModel() {
        viewModel.init(CompanyRepository())
    }

    companion object {
        fun newInstance() = ToursFragment()

        fun startFragment(supportFragmentManager: FragmentManager) {
            supportFragmentManager
                .beginTransaction()
                .replace(android.R.id.content, newInstance())
                .commit()
        }

    }
}