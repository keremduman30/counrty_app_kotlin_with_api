package com.example.countrykotlin.service

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.example.countrykotlin.model.Country
//not veritabanı sınıfı olustuurken sonunda build et ki dosyalar olussun
@Dao
interface CountryDAO {
    //Data Access Object

    @Insert
    suspend fun insertAll(vararg countries:Country):List<Long>
    //insert :  insert into
    //suspend :  corutine ile beraber calıstırmak için yani arka planda main bile beraber calıstırmak için , pause ,resume
    //vararg :  simdi veritabanında birden fazla objede gelebilir bunun sayısını bilmedigimiz için vararg yani otomotik sayi
    //List<Long>:buda modeldeki room database primarykey veriyorduk ya otomatik artan buda o nu donduruyor ve bu bir objeyeesit

    @Query("Select * from country")//burdamodelde model adı neyse tablo ismi o oluyor kuck harflerle
    suspend fun getAllCountries():List<Country>//arka planda calsıacagı için threadlerle birlik te olacak dedik oyuzden suspend

    @Query("select * from country where uuid=:countryId")//burda primaryleye gore alıyor modelde adı uuid old için oyle
    suspend fun getCountry(countryId:Int):Country

    @Query("Delete from country")
    suspend fun deleteAllCountries()

}