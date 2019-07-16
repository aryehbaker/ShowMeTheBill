package com.example.showmethebill;

import java.util.List;

import androidx.databinding.ObservableField;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.Transformations;
import androidx.lifecycle.ViewModel;

public class MiddleWorkTypeIdViewModel extends ViewModel {
    private LiveData<List<middleWorkType>> middleWorkTypeIdLiveData;
    private MutableLiveData <Integer> generalId = new MutableLiveData<>();

    public ObservableField<middleWorkType> oWorkType;
    public MiddleWorkTypeIdViewModel(AppDatabase database){

        middleWorkTypeIdLiveData = Transformations.switchMap(generalId,
                c -> database.middleDao().getMatchingGeneralMiddleTypes(c));

       /* middleWorkTypeIdLiveData =
                database.middleDao().getMatchingGeneralMiddleTypes(id);*/
    }



    public LiveData<List<middleWorkType>> getMiddleWorkTypeOfGeneralId() {
        return middleWorkTypeIdLiveData;
    }
    public void setGeneralId (int id){generalId.setValue(id);}

}
