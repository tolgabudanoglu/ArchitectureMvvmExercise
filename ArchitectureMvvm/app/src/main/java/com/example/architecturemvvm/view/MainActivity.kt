package com.example.architecturemvvm.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.core.content.res.ResourcesCompat
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.architecturemvvm.R
import com.example.architecturemvvm.databinding.ActivityMainBinding
import com.example.architecturemvvm.viewModel.CityViewModel

class MainActivity : AppCompatActivity() {

    // ktx implement private val viewModel:CityViewModel by viewModels()
    private lateinit var viewModel : CityViewModel
    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        viewModel = ViewModelProvider(this).get(CityViewModel::class.java)
        setContentView(binding.root)
    }

    override fun onResume() {
        super.onResume()

        viewModel.getCityData().observe(this, Observer { city->
            binding.ivCity.setImageDrawable(
                ResourcesCompat.getDrawable(resources,city.img,applicationContext.theme)
            )
            binding.tvCityName.text = city.name
            binding.tvCityPopulation.text = city.population.toString()
        })
    }
}