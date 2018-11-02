package com.example.esen.carprediction.di.component

import com.example.esen.carprediction.MainActivity
import com.example.esen.carprediction.di.module.CarsApiModule
import dagger.Component

@Component(modules = [CarsApiModule::class])
interface CarsApiComponent {
    fun inject(app: MainActivity)
}