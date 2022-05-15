package com.example.countrykotlin.service

import com.example.countrykotlin.model.Country
import io.reactivex.Single
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

class CountryApiService {
    private val BASE_URL="https://raw.githubusercontent.com/";
    private val api=Retrofit.Builder()
        .baseUrl(BASE_URL)
        .addConverterFactory(GsonConverterFactory.create())//json yapısında olcagını soyledi
        .addCallAdapterFactory(RxJava2CallAdapterFactory.create())//ve rxjava kullancagımzı belirttik
        .build()
        .create(CountryApi::class.java)





     fun getAllCountries(): Single<List<Country>> {
            return  api.getAllCountries();
    }

}