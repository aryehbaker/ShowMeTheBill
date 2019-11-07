package com.example.showmethebill;

import android.app.Application;

import androidx.databinding.ObservableField;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.Transformations;


import java.util.List;

public class EndWorkTypeIdViewModel extends AndroidViewModel {
        private LiveData<List<EndWorkType>> endWorkTypeIdLiveData;
        private MutableLiveData<Integer> middleId = new MutableLiveData<>();
        public ObservableField<EndWorkType> oWorkType;
        public EndWorkTypeIdViewModel(Application application){
            super(application);
            AppDatabase database = AppDatabase.getInstance(this.getApplication());


            endWorkTypeIdLiveData = Transformations.switchMap(middleId,
                    c -> database.endDao().getMatchingMiddleEndTypes(c));

              }



        public LiveData<List<EndWorkType>> getEndWorkTypeOfMiddleId() {
            return endWorkTypeIdLiveData;
        }
        public void setMiddleId (int id){middleId.setValue(id);}



}
