package com.example.showmethebill;

import android.app.Application;

import java.util.List;

import androidx.databinding.ObservableField;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

public class GeneralWorkTypeViewModel extends AndroidViewModel {
    private LiveData<List<generalWorkType>> generalWorkTypeLiveData;
    public ObservableField<generalWorkType> oWorkType;
    public GeneralWorkTypeViewModel(Application application){
        super(application);
        AppDatabase database = AppDatabase.getInstance(this.getApplication());
        generalWorkTypeLiveData =
                 database.generalDao().loadGeneralTypes();
    }



    public LiveData<List<generalWorkType>> getGeneralWorkType() {
        return generalWorkTypeLiveData;
    }
}
