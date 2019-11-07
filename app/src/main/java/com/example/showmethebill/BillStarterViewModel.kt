package com.example.showmethebill

import android.app.Application
import android.view.View
import androidx.lifecycle.*

class BillStarterViewModel(application:Application) : AndroidViewModel(application ) {
    private val _showOneGeneral = MutableLiveData<Boolean>()
    private val _showOneMidddle = MutableLiveData<Boolean>()
    enum class ActiveRecycler {GENERAL,MIDDLE,END}
    private val _setFAB = MutableLiveData<ActiveRecycler>()


    init {
       setterRecyclerToGeneral()

    }
    val getActiveRecycler:ActiveRecycler?  = _setFAB.value
    val getFAB:LiveData<ActiveRecycler>get()=_setFAB

    fun setterRecyclerToGeneral(){
        _showOneGeneral.value = false
        _showOneMidddle.value = false
        _setFAB.value = ActiveRecycler.GENERAL
    }
    fun setRecyclerToMiddle(){
        _showOneGeneral.value = true
        _showOneMidddle.value = false
        _setFAB.value = ActiveRecycler.MIDDLE

    }
    fun setRecyclerToEnd(){
        _showOneGeneral.value = true
        _showOneMidddle.value = true
        _setFAB.value = ActiveRecycler.END

    }


}
