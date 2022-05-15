package com.example.countrykotlin.view_model

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlin.coroutines.CoroutineContext

//burda viewmodel yerine android view model kullanmamızın sebeibi contextin fragmentin mi yoksa activityenin mi olduguun bilinememisidir
abstract  class BaseViewModel(application: Application):AndroidViewModel(application),CoroutineScope {
    //arka plabda yapılacak iş
    private val job= Job()
    override val coroutineContext: CoroutineContext
        get() =job+Dispatchers.Main;  // burda once işini yap dedik sonra main threade don yani activity ile olnalar main secilir

    override fun onCleared() {
        super.onCleared()
        job.cancel();//eger app kapanırsa veya yok olursa bu işlem iptal olacaktır
    }
}