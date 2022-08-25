package com.code.countries.repository

import com.code.countries.repository.model.Country
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory.create
import retrofit2.http.GET

class NetworkData {
    private val countryBaseUrl = "https://gist.githubusercontent.com/peymano-wmt/"

    suspend fun getCountryData(): CountryResult {
        Retrofit.Builder().baseUrl(countryBaseUrl).addConverterFactory(create()).build()
            .create(CountryEndpoint::class.java).getCountryData().let { response ->
                return if (response.isSuccessful) {
                    CountryResult.Success(response.body()!!)
                } else {
                    CountryResult.Error(response.errorBody().toString())
                }
        }
    }
}

sealed class CountryResult {
    data class Success(val country: List<Country>): CountryResult()
    data class Error(val errorMsg: String): CountryResult()
}

interface CountryEndpoint {
    @GET("32dcb892b06648910ddd40406e37fdab/raw/db25946fd77c5873b0303b858e861ce724e0dcd0/countries.json")
    suspend fun getCountryData(): Response<List<Country>>
}
