package com.example.showmethebill;

import android.app.Application;

import java.util.List;

import androidx.databinding.ObservableField;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

public class MiddleWorkTypeViewModel extends AndroidViewModel {
    private LiveData<List<middleWorkType>> middleWorkTypeLiveData;
    public ObservableField<middleWorkType> oWorkType;
    public MiddleWorkTypeViewModel(Application application){
        super(application);
        AppDatabase database = AppDatabase.getInstance(this.getApplication());
        middleWorkTypeLiveData =
                database.middleDao().getAllMiddleTypes();
    }



    public LiveData<List<middleWorkType>> getGeneralWorkType() {
        return middleWorkTypeLiveData;
    }
}