package com.example.countrykotlin.util

import android.content.Context
import android.widget.ImageView
import androidx.databinding.BindingAdapter
import androidx.swiperefreshlayout.widget.CircularProgressDrawable
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.example.countrykotlin.R

//image için extensions yazdık

/*
data binding oncesi image alımı
 */
fun ImageView.downloadFromUrl(url:String?,progressDrawable: CircularProgressDrawable){
    //oncellikle bu resim yuklenmese ne olacak
    val options=RequestOptions()
        .placeholder(progressDrawable)//sırf context için boyle yappılıd
        .error(R.mipmap.ic_launcher_round)

    //simdi hata yoksa
    Glide.with(context)
        .setDefaultRequestOptions(options)
        .load(url)
        .into(this)//bu image view hangi fragmentteyse this olarak onu alacak cunku extensions

}
fun placeHolderProgressbar(context:Context):CircularProgressDrawable{
    return CircularProgressDrawable(context).apply {
        strokeWidth=8f
        centerRadius=40f
        start();

    };
}
/*
data bingind sonrası imageview alımıı
simdi biz bu fonksiyonu xml de calıstırmak istiyorsak basına @BindingAdapter yazmamaız lazım ve projesi build et

 */
@BindingAdapter("android:downloadUrl")
fun downloadImage(view: ImageView,url: String?){
    view.downloadFromUrl(url, placeHolderProgressbar(view.context));
}