package com.example.countrykotlin.util

import android.content.Context
import android.content.SharedPreferences
import androidx.core.content.edit
import androidx.preference.PreferenceManager
import androidx.room.Room
import com.example.countrykotlin.service.CountryDatabase

class CustomSharedPreferences{

    companion object {
        private var sharedPreferences:SharedPreferences?=null;
        private val PREFERENCES_TIME="preferences_time"

        @Volatile private var instance:CustomSharedPreferences?=null

        operator fun invoke(context: Context):CustomSharedPreferences = instance?: synchronized(Any()){
            instance ?: makeCustomSharedPreferences(context).also {
                instance=it;
            };
        }
        private fun makeCustomSharedPreferences(context: Context):CustomSharedPreferences{
            sharedPreferences=PreferenceManager.getDefaultSharedPreferences(context);
            return CustomSharedPreferences();
        }

    }
    fun saveTime(time:Long){
        //normalde sharedta edit().commit() yapıyorduk ama burda biz commiti otomatik true yptıkyani dire uygulama
        sharedPreferences?.edit(commit = true){
                putLong(PREFERENCES_TIME,time)
        }
    }

    fun getTime()= sharedPreferences?.getLong(PREFERENCES_TIME,0);//zamanı aldık
}