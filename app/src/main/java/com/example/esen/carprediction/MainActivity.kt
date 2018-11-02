package com.example.esen.carprediction

import android.support.v7.app.AppCompatActivity

import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentPagerAdapter
import android.os.Bundle
import com.example.esen.carprediction.data.remote.api.CarsApiService
import com.example.esen.carprediction.di.component.DaggerCarsApiComponent
import com.example.esen.carprediction.di.module.CarsApiModule

import kotlinx.android.synthetic.main.activity_main.*
import javax.inject.Inject


val NUMBER_OF_SLIDES = 10

class MainActivity : AppCompatActivity(), BrandsFragment.OnListFragmentInteractionListener {
    @Inject
    lateinit var carsFetcherService: CarsApiService

    private var mSectionsPagerAdapter: SectionsPagerAdapter? = null

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

    override fun onBrandListInteraction(brand: String) {

    }


    inner class SectionsPagerAdapter(fm: FragmentManager) : FragmentPagerAdapter(fm) {
        override fun getItem(position: Int): Fragment {
            val brands = carsFetcherService.getBrands()
            return when(position) {
                1 -> BrandsFragment.newInstance(brands)
                else -> BrandsFragment.newInstance(brands)
            }

        }

        override fun getCount(): Int {
            return NUMBER_OF_SLIDES
        }
    }
}
