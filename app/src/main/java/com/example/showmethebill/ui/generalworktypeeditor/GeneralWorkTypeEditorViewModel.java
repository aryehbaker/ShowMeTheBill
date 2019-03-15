package com.example.showmethebill.ui.generalworktypeeditor;

import android.app.Application;

import com.example.showmethebill.AppDatabase;
import com.example.showmethebill.generalWorkType;

import androidx.databinding.ObservableField;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

public class GeneralWorkTypeEditorViewModel extends ViewModel {
    private LiveData<generalWorkType> generalWorkTypeLiveData;
    public ObservableField <generalWorkType> oWorkType;
    public GeneralWorkTypeEditorViewModel(){super();}
   public GeneralWorkTypeEditorViewModel(AppDatabase database, int id) {
           generalWorkTypeLiveData = database.generalDao().getAGeneralWorkType(id);
    }


    public LiveData<generalWorkType> getGeneralWorkType() {
        return generalWorkTypeLiveData;
    }
}
