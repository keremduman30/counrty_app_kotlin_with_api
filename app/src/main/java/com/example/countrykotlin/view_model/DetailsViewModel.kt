package com.example.countrykotlin.view_model

import android.app.Application
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.countrykotlin.model.Country
import com.example.countrykotlin.service.CountryDatabase
import kotlinx.coroutines.launch
import java.util.*

class DetailsViewModel(application: Application):BaseViewModel(application) {
    val countryLiveData=MutableLiveData<Country>()


    fun getDataFromRoom(uuid: Int){
        launch {
            val country=CountryDatabase(getApplication()).countryDao().getCountry(uuid);
            countryLiveData.value=country

        }

    }
}