package com.example.esen.carprediction.data.remote.api

import android.content.Context
import com.example.esen.carprediction.data.remote.models.Brands
import com.squareup.moshi.Moshi
import com.squareup.moshi.Types
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory

interface CarsApiService {
    fun getBrands(): ArrayList<String>?
}

class CarsApiServiceMock(val context: Context) : CarsApiService {
    private var brands: Brands?

    init {
        brands = getBrandsJson()
    }

    private fun getBrandsJson(): Brands? {
        val listStringType = Types.newParameterizedType(
                List::class.java,
                String::class.java
        )
        val json = context.assets.open("json/result.json").bufferedReader().use { it.readText() }
        val moshi = Moshi.Builder().add(KotlinJsonAdapterFactory()).build()
        val adapter = moshi.adapter<Brands>(listStringType)
        return adapter.fromJson(json)
    }

    override fun getBrands(): ArrayList<String>? {
        return brands
    }
}