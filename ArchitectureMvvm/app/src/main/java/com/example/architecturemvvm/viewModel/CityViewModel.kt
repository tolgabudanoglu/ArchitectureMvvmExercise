package com.example.architecturemvvm.viewModel

import android.os.Looper
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.architecturemvvm.model.City
import com.example.architecturemvvm.model.CityDataProvider

class CityViewModel: ViewModel() {

    private val cityData = MutableLiveData<City>()
    private val cities = CityDataProvider().getCities()
    private var currentIdnex = 0
    private val delay = 2000L

    init {
        loop()
    }
    fun getCityData(): LiveData<City> = cityData

    private fun loop(){
        android.os.Handler(Looper.getMainLooper()).postDelayed({
            updateCity()

        },delay)
    }

    private fun updateCity() {
        currentIdnex++
        currentIdnex %=cities.size

        cityData.value = cities[currentIdnex]

        loop()
    }
}