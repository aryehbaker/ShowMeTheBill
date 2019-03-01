package com.example.showmethebill.ui.generalworktypeeditor;

import android.app.Application;

import com.example.showmethebill.AppDatabase;
import com.example.showmethebill.generalWorkType;

import androidx.databinding.ObservableField;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

public class GeneralWorkTypeEditorViewModel extends AndroidViewModel {
    private LiveData<generalWorkType> generalWorkTypeLiveData;
    public ObservableField <generalWorkType> oWorkType;
    private int mId;
    public GeneralWorkTypeEditorViewModel(Application application){super(application);}
    public GeneralWorkTypeEditorViewModel(Application application,AppDatabase database, int Id) {
           super(application);
           mId = Id;
           generalWorkTypeLiveData = database.generalDao().getAGeneralWorkType(Id);
    }


    public LiveData<generalWorkType> getGeneralWorkType() {
        return generalWorkTypeLiveData;
    }
}
