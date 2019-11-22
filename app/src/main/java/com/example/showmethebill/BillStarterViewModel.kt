package com.example.showmethebill

import android.app.Application
import android.view.View
import androidx.lifecycle.*

class BillStarterViewModel(application: Application) : AndroidViewModel(application) {
    enum class ActiveRecycler { GENERAL, MIDDLE, END }

    private val _setRecycler = MutableLiveData<ActiveRecycler>()


    init {
        setRecyclerToGeneral()

    }

    val getActiveRecycler: ActiveRecycler? = _setRecycler.value
    val getActiveRecyclerLiveData: LiveData<ActiveRecycler> get() = _setRecycler

    fun setRecyclerToGeneral() {
        _setRecycler.value = ActiveRecycler.GENERAL
    }

    fun setRecyclerToMiddle() {
        _setRecycler.value = ActiveRecycler.MIDDLE

    }

    fun setRecyclerToEnd() {
        _setRecycler.value = ActiveRecycler.END

    }


}
