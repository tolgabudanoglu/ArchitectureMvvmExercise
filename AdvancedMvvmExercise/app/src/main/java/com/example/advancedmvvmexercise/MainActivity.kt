package com.example.advancedmvvmexercise

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.activity.viewModels
import androidx.core.widget.doOnTextChanged
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.advancedmvvmexercise.databinding.ActivityMainBinding
import com.example.advancedmvvmexercise.model.LoadingState
import com.example.advancedmvvmexercise.view.OrderAdapter
import com.example.advancedmvvmexercise.viewModel.MainViewModel

class MainActivity : AppCompatActivity() {

    private lateinit var binding:ActivityMainBinding
    private val viewModel: MainViewModel by viewModels()
    private val adapter = OrderAdapter(arrayListOf())
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }

    override fun onResume() {
        super.onResume()

        initializeUI()
        initializeObservers()

        viewModel.onViewReady()
    }

    private fun initializeUI() {
        binding.rvOrder.adapter = adapter
        binding.rvOrder.layoutManager = LinearLayoutManager(this)

        binding.etSearch.doOnTextChanged { text, start, before, count ->
            viewModel.onSearchQuery(text.toString())
        }
    }

    private fun initializeObservers() {
        viewModel.loadingStateLiveData.observe(this, Observer {
            onLoadingStateChanged(it)
        })

        viewModel.ordersLiveData.observe(this, Observer {
            adapter.updateOrders(it)
        })
    }

    private fun onLoadingStateChanged(state: LoadingState) {
        binding.etSearch.visibility = if (state == LoadingState.LOADED) View.VISIBLE else View.GONE
        binding.rvOrder.visibility = if (state == LoadingState.LOADED) View.VISIBLE else View.GONE
        binding.tvError.visibility = if (state == LoadingState.ERROR) View.VISIBLE else View.GONE
        binding.pbLoading.visibility = if (state == LoadingState.LOADING) View.VISIBLE else View.GONE
    }
}