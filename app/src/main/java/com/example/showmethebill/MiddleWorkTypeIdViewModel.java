package com.example.showmethebill;

import java.util.List;

import androidx.databinding.ObservableField;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

public class MiddleWorkTypeIdViewModel extends ViewModel {
    private LiveData<List<middleWorkType>> middleWorkTypeIdLiveData;
    public ObservableField<middleWorkType> oWorkType;
    public MiddleWorkTypeIdViewModel(AppDatabase database, int id){

        middleWorkTypeIdLiveData =
                database.middleDao().getMatchingMiddleTypes(id);
    }



    public LiveData<List<middleWorkType>> getMiddleWorkTypeOfGeneralId() {
        return middleWorkTypeIdLiveData;
    }

}
