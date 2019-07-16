package com.example.showmethebill;

import android.app.Application;

import java.util.List;

import androidx.databinding.ObservableField;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.Transformations;

public class MiddleWorkTypeViewModel extends AndroidViewModel {
    private LiveData<List<middleWorkType>> middleWorkTypeLiveData;
    private LiveData<List<middleWorkType>> middleWorkTypeIdLiveData;
    private MutableLiveData<Integer> generalId = new MutableLiveData<>();
    private MutableLiveData<middleWorkType>middleOneLiveData = new MutableLiveData<>();

    public ObservableField<com.example.showmethebill.middleWorkType> oWorkType;
    public MiddleWorkTypeViewModel(Application application){
        super(application);
        AppDatabase database = AppDatabase.getInstance(this.getApplication());
        middleWorkTypeLiveData =
                database.middleDao().getAllMiddleTypes();
        middleWorkTypeIdLiveData = Transformations.switchMap(generalId,
                c -> database.middleDao().getMatchingGeneralMiddleTypes(c));
    }



    public LiveData<List<middleWorkType>> getGeneralWorkType() {

        return middleWorkTypeLiveData;
    }
    public LiveData<List<middleWorkType>> getMiddleWorkTypeOfGeneralId() {
        return middleWorkTypeIdLiveData;
    }
    public void setGeneralId (int id){generalId.setValue(id);}
    public void setMiddleOneLiveData (middleWorkType middleWorkType){
        middleOneLiveData.setValue(middleWorkType);
    }
    public LiveData<middleWorkType> getMiddleOneLiveData(){
        return middleOneLiveData;
    }


}