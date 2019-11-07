package com.example.showmethebill;

import android.app.Application;

import java.util.List;

import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

public class GeneralWorkTypeViewModel extends AndroidViewModel {
    private AppDatabase appDatabase;
    private MutableLiveData<GeneralWorkType> generalOneLiveData = new MutableLiveData<>();
    private final LiveData<List<GeneralWorkType>> generalWorkTypeLiveData;
    public GeneralWorkTypeViewModel(Application application){
        super(application);
        appDatabase = AppDatabase.getInstance(getApplication());
        generalWorkTypeLiveData = appDatabase.generalDao().loadGeneralTypes();
    }



    public LiveData<List<GeneralWorkType>> getGeneralWorkType() {
        return generalWorkTypeLiveData;
    }

    public void setGeneralOneLiveData(GeneralWorkType general) {
        generalOneLiveData.setValue(general);
    }
    public LiveData<GeneralWorkType>getGeneralOneLiveData(){
        return generalOneLiveData;
    }
}
