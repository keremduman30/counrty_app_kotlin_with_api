package com.example.countrykotlin.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

//burda seralizednameler retrofitten alınan verirler json yapıda oldugu için keyleri ile birlikte belirttik
//room kullnarak sqlite da kayıt yapmak içim suun adlarını yazmak lazım onuda columınfo  ile belirttik
//primary keyi aldık cunku hangi tablo oldugunu bu bize vverecek otomitk artan ozellgide vermis olduk

@Entity
data class Country(
    @ColumnInfo(name = "name")
    @SerializedName("name")
    val countryName: String?,
    @ColumnInfo(name = "region")
    @SerializedName("region")
    val countryRegion: String?,
    @ColumnInfo(name = "capital")
    @SerializedName("capital")
    val countryCapital: String?,
    @ColumnInfo(name = "currency")
    @SerializedName("currency")
    val countryCurrency: String?,
    @ColumnInfo(name = "language")
    @SerializedName("language")
    val countryLanguage: String?,
    @ColumnInfo(name = "flag")
    @SerializedName("flag")
    val imageUrl: String?
){
    @PrimaryKey(autoGenerate = true)
    var uuid:Int=0
}
