package com.tour.serp.utils

import android.util.Log
import com.tour.serp.BuildConfig
import kotlin.reflect.KClass

fun debug(clazz: Any, data: Any) {
    if (BuildConfig.DEBUG) Log.d("${clazz::class.simpleName} ::: ", data.toString())
}

fun debug(clazz: KClass<*>, data: Any) {
    if (BuildConfig.DEBUG) Log.d("${clazz.simpleName} ::: ", data.toString())
}