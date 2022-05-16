package com.example.countrykotlin.service

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.countrykotlin.model.Country




@Database(entities = arrayOf(Country::class), version = 1)
abstract class CountryDatabase :RoomDatabase(){
    //simdi yazdıgımızı dao sınıfını abstaract oolarak belirtelim zaten sınıf birinterface
    abstract fun countryDao():CountryDAO

    //singleton
    companion object {
        @Volatile private var instance:CountryDatabase?=null
       
        operator fun  invoke(context: Context)= instance?: synchronized(Any()){
            instance?: makeDatabase(context).also {
                instance=it;
            };
        }
        private fun makeDatabase(context:Context)=Room.databaseBuilder(context.applicationContext,CountryDatabase::class.java,"countrydatabase").build()
    }
}