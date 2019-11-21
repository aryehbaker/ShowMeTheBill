package com.example.showmethebill;

import android.app.Application;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.databinding.ObservableField;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.Transformations;
import androidx.lifecycle.ViewModel;

public class MiddleWorkTypeIdViewModel extends AndroidViewModel {
    private LiveData<List<MiddleWorkType>> middleWorkTypeIdLiveData;
    private MutableLiveData<Integer> generalId = new MutableLiveData<>();
    private MutableLiveData<MiddleWorkType> middleOneLiveData = new MutableLiveData<>();


    public ObservableField<MiddleWorkType> oWorkType;

    public MiddleWorkTypeIdViewModel(Application application) {
        super(application);
        AppDatabase database = AppDatabase.getInstance(application);
        middleWorkTypeIdLiveData = Transformations.switchMap(generalId,
                c -> database.middleDao().getMatchingGeneralMiddleTypes(c));

       /* middleWorkTypeIdLiveData =
                database.middleDao().getMatchingGeneralMiddleTypes(id);*/
    }


    public void setMiddleOneLiveData(MiddleWorkType middleOneLiveData) {
        this.middleOneLiveData.setValue(middleOneLiveData);//needs more work
    }

    public LiveData<MiddleWorkType> getMiddleOneLiveData() {
        return middleOneLiveData;
    }

    public LiveData<List<MiddleWorkType>> getMiddleWorkTypeOfGeneralId() {
        return middleWorkTypeIdLiveData;
    }

    public void setGeneralId(int id) {
        generalId.setValue(id);
    }

}
