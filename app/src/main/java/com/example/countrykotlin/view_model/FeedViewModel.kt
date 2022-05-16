package com.example.countrykotlin.view_model

import android.app.Application
import android.widget.Toast
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.countrykotlin.model.Country
import com.example.countrykotlin.service.CountryApiService
import com.example.countrykotlin.service.CountryDatabase
import com.example.countrykotlin.util.CustomSharedPreferences
import io.reactivex.Scheduler
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable
import io.reactivex.observers.DisposableSingleObserver
import io.reactivex.schedulers.Schedulers
import kotlinx.coroutines.launch


class FeedViewModel(application: Application):BaseViewModel(application) {
    private val countryApiService=CountryApiService()// api servisimiz
  
    private val disposable=CompositeDisposable()

    private var customSharedPreferences=CustomSharedPreferences(getApplication())

    //mutable :degistirilebilir
    val countries=MutableLiveData<List<Country>>()
    val countryError=MutableLiveData<Boolean>() //hata ya olacak yada olmuyacak
    val countryLoading=MutableLiveData<Boolean>()//yuklenşyormu yuklenmiyormu

    //simdi 10dk bir yenile işlemi icin nano saniye ye ihtiyacımız var
    private var refreshTime= 10 * 60 * 1000 * 1000 * 1000L//bu nano cinsinden 10 dk gecti verir cok hassas oldugu içn boyle


    fun refreshData(){
        val updateTime=customSharedPreferences.getTime();
        if (updateTime!=null && updateTime!=0L && System.nanoTime()-updateTime<refreshTime){//kayıt edildigi dk ile system dk cıkardık ve bu 10dan
            //kcukse
                print("girmedi********************")
            getDataFromSql()
        }
        else{
            getDataFromApi()
        }




    }
    fun refreshFromApi(){
        getDataFromApi();
    }

    private fun getDataFromSql() {
        countryLoading.value=true;
        launch {
            val countries=CountryDatabase(getApplication()).countryDao().getAllCountries()
            showCountries(countries);
            Toast.makeText(getApplication(),"Countries from Sqlite", Toast.LENGTH_SHORT).show()

        }
    }


    private fun getDataFromApi(){
        countryLoading.value=true;
        disposable.add(
            countryApiService.getAllCountries()
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(object:DisposableSingleObserver<List<Country>>(){
                    override fun onSuccess(t: List<Country>) {
                        storeInSqlite(t)
                        Toast.makeText(getApplication(),"Countries from Api", Toast.LENGTH_SHORT).show()

                    }

                    override fun onError(e: Throwable) {
                        countryLoading.value=false;
                        countryError.value=true;
                        e.printStackTrace();
                    }

                })

        )

    }
    private fun showCountries(t:List<Country>){
        countries.value=t;
        countryError.value=false;
        countryLoading.value=false;
    }
    private fun storeInSqlite(list:List<Country>){
        launch {
            val dao=CountryDatabase(getApplication()).countryDao()
            dao.deleteAllCountries();
           val listLong= dao.insertAll(*list.toTypedArray())//bu listeyi tek tek ekle kotline ozgu listeyi tekil hale geitiyiyor. individual: tek tek hale
            var i=0;
            while (i<list.size){
                list[i].uuid=listLong[i].toInt()
                i+=1;
            }
            showCountries(list);
        }
        customSharedPreferences.saveTime(System.nanoTime())
    }

    override fun onCleared() {
        super.onCleared()
        disposable.clear();
    }

}