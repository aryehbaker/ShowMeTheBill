package com.example.showmethebill

import android.app.Application
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData

public class AndroidApplication : Application() {
    enum class ActiveRecycler {GENERAL,MIDDLE,END}
    private val _setFAB = MutableLiveData<ActiveRecycler>()


    init {
        setterRecyclerToGeneral()

    }
    val getActiveRecycler:ActiveRecycler?  = _setFAB.value
    val getFAB: LiveData<ActiveRecycler>
        get()=_setFAB

    fun setterRecyclerToGeneral(){
        _setFAB.value = ActiveRecycler.GENERAL
    }
    fun setRecyclerToMiddle(){
        _setFAB.value = ActiveRecycler.MIDDLE

    }
    fun setRecyclerToEnd(){
        _setFAB.value = ActiveRecycler.END

    }



    override fun onCreate() {
        super.onCreate()
        instance = this
    }

    companion object {
        var instance: AndroidApplication? = null
            private set
    }
}
