package com.example.countrykotlin.service

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.countrykotlin.model.Country

/*
    burda veritabanı oldugnu ve birden fazla veriyabanı modeli olabilecegi için bunun list içinde kactane verimodeli varsa onu
    eklememizi ve verisonu yazdıgmızı belirttil

    singleton : içierisinde tek bir obje olusturulabilen bir sınıftı ve eger obje yok sa olusuturuyordu ve eger varsa
    o olusutrulan obje caıgrılıyordu  ve appin herhanhi bir yerinde buna ulasılabilir bunu her yerden ulasabilmek için
    basına companion object olarak belirtiyoeuz

    volatile : volatile belirtilen bir parametre veya fonksiyon farklı threadlerde gorunur yapıyor biz coroutins kulandıgmızı içim
    bunu kullandık

    synchronized: buda aynı anda iki thread bunu olsuturamaz yani iki thread aynı anda geldi digerini beklemek zorundu
    yani async nin tam tersi

 */


@Database(entities = arrayOf(Country::class), version = 1)
abstract class CountryDatabase :RoomDatabase(){
    //simdi yazdıgımızı dao sınıfını abstaract oolarak belirtelim zaten sınıf birinterface
    abstract fun countryDao():CountryDAO

    //singleton
    companion object {
        @Volatile private var instance:CountryDatabase?=null
        //simdi burda instance varsa yani bu sınıf olusuturulmussa super ile dondur ama olsuturmamıs ise (:) senyronize bir
        //sekilde asagıdaki adımları yap
        operator fun  invoke(context: Context)= instance?: synchronized(Any()){
            instance?: makeDatabase(context).also {
                //ve ilaveten bu istance database esitle
                instance=it;
            };
        }
        //burdaki context.applicationContext i fragment veya activitynenin mi contexibelli degil  yuzden app ckmemisi için boyle yaptık
        private fun makeDatabase(context:Context)=Room.databaseBuilder(context.applicationContext,CountryDatabase::class.java,"countrydatabase").build()
    }
}