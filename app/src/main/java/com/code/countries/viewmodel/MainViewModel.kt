package com.code.countries.viewmodel

import android.util.Log
import android.view.View
import androidx.lifecycle.ViewModel
import com.code.countries.adapter.CountryAdapter
import com.code.countries.databinding.FragmentMainBinding
import com.code.countries.repository.CountryResult
import com.code.countries.repository.NetworkData
import com.code.countries.repository.model.Country
import kotlinx.coroutines.*
import kotlin.coroutines.CoroutineContext

class MainViewModel: ViewModel(), CoroutineScope {
    private val job = Job()
    private val handler = CoroutineExceptionHandler { _, exception ->
        Log.d("Coroutine exception -->", "${exception.message}")
    }
    private var countryList: List<Country>? = null

    override val coroutineContext: CoroutineContext
        get() = Dispatchers.Main + job + handler

    override fun onCleared() {
        super.onCleared()
        job.cancel()
    }

    fun loadCountryData(binding: FragmentMainBinding) {
        launch {
            if (countryList.isNullOrEmpty()) {
                try {
                    showProgress(binding)
                    withContext(Dispatchers.IO) {
                        NetworkData().getCountryData()
                    }.let { countryResult ->
                        if (countryResult is CountryResult.Success)
                            countryList = countryResult.country

                        if (countryResult is CountryResult.Error)
                            Log.d("Data error", countryResult.errorMsg)
                    }
                } finally {
                    hideProgress(binding)
                }
            }
            if (countryList!!.isNotEmpty()) {
                binding.recycler.adapter = CountryAdapter(countryList)
            } else {
                showNoData(binding)
            }
        }
    }

    private fun showNoData(binding: FragmentMainBinding) {
        binding.noData.visibility = View.VISIBLE
    }

    private fun showProgress(binding: FragmentMainBinding) {
        binding.progress.visibility = View.VISIBLE
    }

    private fun hideProgress(binding: FragmentMainBinding) {
        binding.progress.visibility = View.GONE
    }
}