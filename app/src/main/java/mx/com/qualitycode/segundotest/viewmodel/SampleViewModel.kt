package mx.com.qualitycode.segundotest.viewmodel

import android.app.Application
import androidx.lifecycle.MutableLiveData

class SampleViewModel(application: Application) :  BaseAndroidViewModel(application)  {

    var message : MutableLiveData<String> = MutableLiveData()

    init {
        message.value = ""
    }

}