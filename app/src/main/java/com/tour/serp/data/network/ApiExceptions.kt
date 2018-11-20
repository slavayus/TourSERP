package com.tour.serp.data.network

import android.widget.Toast
import com.tour.serp.App
import com.tour.serp.R
import java.io.IOException

interface ApiExceptions {
    fun handleException(exception: Exception) {
        when (exception) {
            is IOException -> {
                Toast.makeText(App.context, R.string.error_check_internet_connection, Toast.LENGTH_SHORT).show()
            }
            else -> {
                Toast.makeText(App.context, R.string.error_unknown_http_error, Toast.LENGTH_SHORT).show()
            }
        }
    }
}