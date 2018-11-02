package com.example.esen.carprediction

import android.support.v7.app.AppCompatActivity

import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentPagerAdapter
import android.os.Bundle
import android.util.Log
import com.example.esen.carprediction.data.models.PredictionParams
import com.example.esen.carprediction.data.remote.api.CarsApiService
import com.example.esen.carprediction.di.component.DaggerCarsApiComponent
import com.example.esen.carprediction.di.module.CarsApiModule

import kotlinx.android.synthetic.main.activity_main.*
import javax.inject.Inject


const val NUMBER_OF_SLIDES = 10

class MainActivity : AppCompatActivity(), ListFragment.OnListFragmentInteractionListener {
    @Inject
    lateinit var carsFetcherService: CarsApiService

    private var mSectionsPagerAdapter: SectionsPagerAdapter? = null
    private val mPredictionParams = PredictionParams()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initView()
        injectCarsApiService()
    }

    private fun initView() {
        setContentView(R.layout.activity_main)

        setSupportActionBar(toolbar)
        mSectionsPagerAdapter = SectionsPagerAdapter(supportFragmentManager)

        container.adapter = mSectionsPagerAdapter
    }

    private fun injectCarsApiService() {
        DaggerCarsApiComponent
                .builder()
                .carsApiModule(CarsApiModule(applicationContext))
                .build()
                .inject(this)
    }

    override fun onItemListInteraction(field: String, value: String) {
        when (field) {
            "brand" -> { mPredictionParams.brand = value }
            "wheel" -> { mPredictionParams.wheel = value }
            "year" -> { mPredictionParams.year = value }
            "transmission" -> { mPredictionParams.transmission = value }
            "mileage" -> { mPredictionParams.mileage = value }
            "capacity" -> { mPredictionParams.capacity = value }
            "drive" -> { mPredictionParams.drive = value }
            "color" -> { mPredictionParams.color = value }
            "carcass" -> { mPredictionParams.carcass = value }
            "fuel" -> { mPredictionParams.fuel = value }
        }
    }

    val x: Callback = {
        Log.d("tag", "dicks")
    }


    inner class SectionsPagerAdapter(fm: FragmentManager) : FragmentPagerAdapter(fm) {
        override fun getItem(position: Int): Fragment {
            val brands = carsFetcherService.getBrands()
            return when (position) {
                1 -> ListFragment.newInstance("brand", brands)
                else -> ListFragment.newInstance("brand", brands)
            }

        }

        override fun getCount(): Int {
            return NUMBER_OF_SLIDES
        }
    }
}
