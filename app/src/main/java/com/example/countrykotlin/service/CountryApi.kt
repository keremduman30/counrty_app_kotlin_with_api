package com.example.countrykotlin.service

import com.example.countrykotlin.model.Country
import io.reactivex.Single
import retrofit2.http.GET

interface CountryApi {
    ////https://raw.githubusercontent.com/atilsamancioglu/IA19-DataSetCountries/master/countrydataset.json
    /*
    burda bizz call ve observable da kullanabilirdik ama biz rxjavanın single kullandık cunku bu veriyiyi gene observable getiirir
    ama sadece internetten bir kere ceker
    yani rxjavanın bir suru metoduvar observable single vb biz surekli canlı veri istemiyoz bir kere cekip bunu sonradan
    sql dan cekecez o yuzden single kulandık
     */

    @GET("atilsamancioglu/IA19-DataSetCountries/master/countrydataset.json")
    fun getAllCountries():Single<List<Country>>

}