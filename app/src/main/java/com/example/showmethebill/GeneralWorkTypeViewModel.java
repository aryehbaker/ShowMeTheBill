package com.example.showmethebill;

import android.app.Application;

import java.util.List;

import androidx.databinding.ObservableField;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

public class GeneralWorkTypeViewModel extends AndroidViewModel {
    private LiveData<List<generalWorkType>> generalWorkTypeLiveData;
    private MutableLiveData<generalWorkType> generalOneLiveData;
    public GeneralWorkTypeViewModel(Application application){
        super(application);
        AppDatabase database = AppDatabase.getInstance(this.getApplication());
        generalWorkTypeLiveData =
                 database.generalDao().loadGeneralTypes();
    }



    public LiveData<List<generalWorkType>> getGeneralWorkType() {
        return generalWorkTypeLiveData;
    }

    public void setGeneralOneLiveData(generalWorkType generalOneLiveData) {
        this.generalOneLiveData.setValue(generalOneLiveData);
    }
    public LiveData<generalWorkType>getGeneralOneLiveData(){
        return generalOneLiveData;
    }
}
