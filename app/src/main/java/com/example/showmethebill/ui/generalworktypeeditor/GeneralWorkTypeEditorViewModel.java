package com.example.showmethebill.ui.generalworktypeeditor;

import com.example.showmethebill.AppDatabase;
import com.example.showmethebill.GeneralWorkType;

import androidx.databinding.ObservableField;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

public class GeneralWorkTypeEditorViewModel extends ViewModel {
    private LiveData<GeneralWorkType> generalWorkTypeLiveData;
    public ObservableField <GeneralWorkType> oWorkType;
    public GeneralWorkTypeEditorViewModel(){super();}
   public GeneralWorkTypeEditorViewModel(AppDatabase database, int id) {
           generalWorkTypeLiveData = database.generalDao().getAGeneralWorkType(id);
    }


    public LiveData<GeneralWorkType> getGeneralWorkType() {
        return generalWorkTypeLiveData;
    }
}
