package com.example.showmethebill;

import android.app.Application;

import java.util.List;

import androidx.databinding.ObservableField;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.Transformations;

public class MiddleWorkTypeViewModel extends AndroidViewModel {
    private LiveData<List<MiddleWorkType>> middleWorkTypeLiveData;
    private LiveData<List<MiddleWorkType>> middleWorkTypeIdLiveData;
    private MutableLiveData<Integer> generalId = new MutableLiveData<>();
    private MutableLiveData<MiddleWorkType>middleOneLiveData = new MutableLiveData<>();

    public ObservableField<MiddleWorkType> oWorkType;
    public MiddleWorkTypeViewModel(Application application){
        super(application);
        AppDatabase database = AppDatabase.getInstance(this.getApplication());
        middleWorkTypeLiveData =
                database.middleDao().getAllMiddleTypes();
        middleWorkTypeIdLiveData = Transformations.switchMap(generalId,
                c -> database.middleDao().getMatchingGeneralMiddleTypes(c));
    }



    public LiveData<List<MiddleWorkType>> getGeneralWorkType() {

        return middleWorkTypeLiveData;
    }
    public LiveData<List<MiddleWorkType>> getMiddleWorkTypeOfGeneralId() {
        return middleWorkTypeIdLiveData;
    }
    public void setGeneralId (int id){generalId.setValue(id);}
    public void setMiddleOneLiveData (MiddleWorkType middleWorkType){
        middleOneLiveData.setValue(middleWorkType);
    }
    public LiveData<MiddleWorkType> getMiddleOneLiveData(){
        return middleOneLiveData;
    }


}