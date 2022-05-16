package com.example.countrykotlin.service

import com.example.countrykotlin.model.Country
import io.reactivex.Single
import retrofit2.http.GET

interface CountryApi {
  

    @GET("atilsamancioglu/IA19-DataSetCountries/master/countrydataset.json")
    fun getAllCountries():Single<List<Country>>

}