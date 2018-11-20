package com.tour.serp.ui.tour

import android.databinding.DataBindingUtil
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.tour.serp.R
import com.tour.serp.databinding.FragmentToursBinding

class ToursFragment : Fragment() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val binding = DataBindingUtil.inflate<FragmentToursBinding>(inflater, R.layout.fragment_tours, container, false)

        return binding.root
    }

    companion object {
        fun newInstance() = ToursFragment()

        fun startFragment(supportFragmentManager: FragmentManager) {
            supportFragmentManager
                .beginTransaction()
                .replace(android.R.id.content, newInstance())
                .addToBackStack(null)
                .commit()
        }

    }
}