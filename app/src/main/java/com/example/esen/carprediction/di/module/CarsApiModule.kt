package com.example.esen.carprediction.di.module

import android.content.Context
import com.example.esen.carprediction.data.remote.api.CarsApiService
import com.example.esen.carprediction.data.remote.api.CarsApiServiceMock
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class CarsApiModule(val context: Context) {
    @Provides
    fun getCarsApiService(): CarsApiService {
      return CarsApiServiceMock(context)
    }
}